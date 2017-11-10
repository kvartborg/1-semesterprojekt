package maga.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import maga.item.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import maga.character.Cook;
import maga.character.Player;
import maga.character.Trump;
import maga.environment.Environment;
import maga.environment.Room;
import maga.Game;
import maga.item.Steak;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GameState {

    /**
     * Creates a list of available items
     */
    public static List<Item> items = new ArrayList<Item>();

    /**
     * Saves the game in an XML file
     *
     * @param steps
     * @param startTime
     * @param bonusTime
     * @param points
     * @param player
     * @param trump
     * @param cook
     * @param environment
     */
    public static void save(int steps, long startTime, long bonusTime, int points, Player player, Trump trump, Cook cook, Environment environment) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element game = doc.createElement("game");
            doc.appendChild(game);

            Element stepsE = doc.createElement("steps");
            stepsE.appendChild(doc.createTextNode(Integer.toString(steps)));
            game.appendChild(stepsE);

            Element startTimeE = doc.createElement("startTime");
            startTimeE.appendChild(doc.createTextNode(Long.toString(startTime)));
            game.appendChild(startTimeE);

            Element bonusTimeE = doc.createElement("bonusTime");
            bonusTimeE.appendChild(doc.createTextNode(Long.toString(bonusTime)));
            game.appendChild(bonusTimeE);

            Element pointsE = doc.createElement("points");
            pointsE.appendChild(doc.createTextNode(Integer.toString(points)));
            game.appendChild(pointsE);

            Element playerE = doc.createElement("player");
            Element roomE = doc.createElement("room");
            roomE.appendChild(doc.createTextNode(player.getCurrentRoom().getName()));
            playerE.appendChild(roomE);
            game.appendChild(playerE);
            playerE.setAttribute("tweeted", Boolean.toString(player.hasTweeted()));

            Element itemsE = doc.createElement("items");
            playerE.appendChild(itemsE);
            for (Item item : player.getItems()) {
                Element itemNode = doc.createElement("item");
                itemNode.setAttribute("name", item.getName());
                itemsE.appendChild(itemNode);
            }

            Element trumpE = doc.createElement("trump");
            Element trumpRoomE = doc.createElement("room");
            trumpRoomE.appendChild(doc.createTextNode(trump.getCurrentRoom().getName()));
            trumpE.appendChild(trumpRoomE);
            game.appendChild(trumpE);

            Element cookE = doc.createElement("cook");
            Element cookRoomE = doc.createElement("room");
            cookRoomE.appendChild(doc.createTextNode(cook.getCurrentRoom().getName()));
            cookE.appendChild(cookRoomE);
            game.appendChild(cookE);

            Element rooms = doc.createElement("rooms");
            game.appendChild(rooms);

            for (Room room : environment.getRooms().values()) {
                Element roomNode = doc.createElement("room");
                roomNode.setAttribute("name", room.getName());
                roomNode.setAttribute("locked", Boolean.toString(room.isLocked()));
                rooms.appendChild(roomNode);

                Element itemsRoom = doc.createElement("items");
                roomNode.appendChild(itemsRoom);
                for (Item item : room.getItems()) {
                    Element itemNode = doc.createElement("item");
                    itemNode.setAttribute("name", item.getName());
                    itemsRoom.appendChild(itemNode);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/gameState.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {

        }

    }

    /**
     * Loads the game from XML file
     *
     * @param game
     */
    public static void load(Game game) {
        GameState.resetGame(game);
        try {
            File file = new File("gameState.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            game.setSteps(Integer.parseInt(findElementByName(doc, "steps").getTextContent()));
            game.setPoints(Integer.parseInt(findElementByName(doc, "points").getTextContent()));
            game.setStartTime(Long.parseLong(findElementByName(doc, "startTime").getTextContent()));
            game.setBonusTime(Long.parseLong(findElementByName(doc, "bonusTime").getTextContent()));

            NodeList playerList = doc.getElementsByTagName("player");
            Element playerElement = (Element) playerList.item(0);
            if (playerElement.getAttribute("tweeted").equals("true")) {
                game.getPlayer().tweeted();
            }
            game.getPlayer().setCurrentRoom(game.getEnvironment().getRoom(playerElement.getElementsByTagName("room").item(0).getTextContent()));
            NodeList playerItems = playerElement.getElementsByTagName("item");
            for (int i = 0; i < playerItems.getLength(); i++) {
                Element item = (Element) playerItems.item(i);
                game.getPlayer().addItem(GameState.findItem(item.getAttribute("name")));
            }

            NodeList trumpList = doc.getElementsByTagName("trump");
            Element trumpElement = (Element) trumpList.item(0);
            game.getTrump().setCurrentRoom(game.getEnvironment().getRoom(trumpElement.getElementsByTagName("room").item(0).getTextContent()));

            NodeList cookList = doc.getElementsByTagName("cook");
            Element cookElement = (Element) cookList.item(0);
            game.getCook().setCurrentRoom(game.getEnvironment().getRoom(cookElement.getElementsByTagName("room").item(0).getTextContent()));

            NodeList rooms = doc.getElementsByTagName("room");
            for (int i = 0; i < rooms.getLength(); i++) {
                Element roomElement = (Element) rooms.item(i);
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

        } catch (Exception e) {
        }

    }

    /**
     * This method finds an item in items ArrayList
     *
     * @param doc
     * @param name
     * @return Node
     */
    public static Node findElementByName(Document doc, String name) {
        NodeList list = doc.getElementsByTagName(name);
        Element element = (Element) list.item(0);
        return element.getChildNodes().item(0);
    }

    /**
     * Resets the game
     *
     * @param game
     */
    public static void resetGame(Game game) {
        GameState.items.add(new Steak());
        for (Item item : game.getPlayer().getItems()) {
            GameState.items.add(item);
        }
        game.getPlayer().removeItems();

        for (Room room : game.getEnvironment().getRooms().values()) {
            for (Item item : room.getItems()) {
                GameState.items.add(item);
            }
            room.empty();
            room.unlock();
        }
    }

    /**
     * This method makes it possible to search the items arraylist on in
     * GameState
     *
     * @param name
     * @return item
     */
    public static Item findItem(String name) {
        for (Item item : GameState.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

}
