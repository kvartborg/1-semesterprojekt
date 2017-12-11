package maga.environment;

import maga.item.Item;
import maga.item.DummyItem;
import maga.item.Key;
import maga.item.Ketchup;
import maga.item.Computer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import maga.item.NuclearFootball;
import static maga.util.Helper.findItem;
import acq.ILoadable;
import acq.ISerializable;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import maga.Game;

public final class Environment implements ISerializable, ILoadable {

    /**
     * HashMap attribute for rooms and their names
     */
    private final HashMap<String, Room> rooms = new HashMap<>();

    /**
     * This Set keeps all dummy items.
     */
    private final Set<DummyItem> dummyItems = new HashSet<>();

    /**
     * Getter method for accessing a room by its name
     * @param name
     * @return Room
     */
    public Room getRoom(String name) {
        return this.rooms.get(name);
    }

    /**
     * Create a new instance of Environment
     */
    public Environment() {
        this.createRooms();
        this.placeItemsInOvalOffice();
        this.placeItemsInDiningRoom();
        this.placeItemsInSecretServiceRoom();
        this.createDummyItems();
    }

    /**
     * This method creates the different rooms in our game.
     * We create the rooms in our game and sets an exit for each room. We put
     * our room into a HashMap. The method returns the rooms into our HashMap.
     */
    private void createRooms() {
        Room ovalOffice, lobby1, lobby2, kitchen, diningRoom, cleaningRoom, pressBriefingRoom, secretServiceRoom;

        ovalOffice = new Room("Oval office", "in the oval office", 1, 3);
        lobby1 = new Room("Lobby1", "in the first lobby", 1, 2);
        lobby2 = new Room("Lobby2", "in the second lobby", 1, 1);
        kitchen = new Room("Kitchen", "in the kitchen", 0, 1);
        diningRoom = new Room("Dining room", "in the dining room", 0, 2);
        cleaningRoom = new Room("Cleaning room", "in the cleaning room", 1, 0);
        pressBriefingRoom = new Room("Press briefing room", "in the press briefing room", 2, 1);
        secretServiceRoom = new Room("Secret service room", "in the secret service room", 2, 2);

        ovalOffice.setExit("north", lobby1);
        ovalOffice.lock();

        lobby1.setExit("north", lobby2);
        lobby1.setExit("east", secretServiceRoom);
        lobby1.setExit("south", ovalOffice);
        lobby1.setExit("west", diningRoom);

        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("east", lobby1);

        secretServiceRoom.setExit("west", lobby1);

        lobby2.setExit("north", cleaningRoom);
        lobby2.setExit("east", pressBriefingRoom);
        lobby2.setExit("south", lobby1);
        lobby2.setExit("west", kitchen);

        kitchen.setExit("east", lobby2);
        kitchen.setExit("south", diningRoom);

        pressBriefingRoom.setExit("west", lobby2);

        cleaningRoom.setExit("south", lobby2);

        rooms.put(ovalOffice.getName(), ovalOffice);
        rooms.put(lobby1.getName(), lobby1);
        rooms.put(lobby2.getName(), lobby2);
        rooms.put(diningRoom.getName(), diningRoom);
        rooms.put(secretServiceRoom.getName(), secretServiceRoom);
        rooms.put(kitchen.getName(), kitchen);
        rooms.put(pressBriefingRoom.getName(), pressBriefingRoom);
        rooms.put(cleaningRoom.getName(), cleaningRoom);
    }

    /**
     * Places the computer in the oval office
     */
    private void placeItemsInOvalOffice() {
        rooms.get("Oval office").addItems(new Item[]{
            new Computer(),
            new NuclearFootball()
        });
    }

    /**
     * Places the ketchup in the dining room
     */
    private void placeItemsInDiningRoom() {
        rooms.get("Dining room").addItems(new Item[]{
            new Ketchup()
        });
    }

    /**
     * Places the key in the Secret service room
     */
    private void placeItemsInSecretServiceRoom() {
        rooms.get("Secret service room").addItems(new Item[]{
            new Key(rooms.get("Oval office"))
        });
    }

    /**
     * This method creates dummy items and places them into random rooms.
     */
    private void createDummyItems() {
        dummyItems.add(new DummyItem("Egg"));
        dummyItems.add(new DummyItem("Putin-picture-book"));
        dummyItems.add(new DummyItem("Hillary-Clintons-phone"));
        dummyItems.add(new DummyItem("Wig"));
        dummyItems.add(new DummyItem("Mayo"));
        dummyItems.add(new DummyItem("Ivanka-Trumps-sunglasses"));
        for (DummyItem dummyItem : dummyItems) {
            String[] availableRooms = this.rooms.keySet().toArray(new String[this.rooms.size()]);
            String randomRoom = availableRooms[(int) Math.floor(Math.random() * 7)];
            rooms.get(randomRoom).addItem(dummyItem);
        }
    }

    /**
     * Accessor method for the Rooms in the hashmap
     * @return rooms
     */
    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    /**
     * Serialize the environment object to xml
     * @param doc
     * @return xml element
     */
    @Override
    public Document serialize(Document doc) {
        Element rooms = doc.createElement("rooms");

        for (Room room : this.getRooms().values()) {
            rooms.appendChild(room.serialize(doc));
        }

        doc.getDocumentElement().appendChild(rooms);

        return doc;
    }

    /**
     * This method loads environment
     * @param doc
     * @param game
     */
    @Override
    public void load(Document doc, Game game) {
        NodeList list = doc.getElementsByTagName("room");

        for (int i = 0; i < list.getLength(); i++) {
            Element roomElement = (Element) list.item(i);
            Room room = game.getEnvironment().getRoom(roomElement.getAttribute("name"));

            if (roomElement.getAttribute("locked").equals("true")) {
                room.lock();
            }

            NodeList items = roomElement.getElementsByTagName("item");
            for (int j = 0; j < items.getLength(); j++) {
                Element item = (Element) items.item(j);
                room.addItem(findItem(item.getAttribute("name")));
            }
        }
    }
}
