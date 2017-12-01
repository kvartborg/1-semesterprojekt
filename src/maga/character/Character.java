
package maga.character;

import maga.command.Command;
import maga.environment.Environment;
import maga.environment.Room;
import maga.util.Serializable;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import maga.util.Loadable;
import org.w3c.dom.NodeList;


public abstract class Character implements Serializable, Loadable {

    /**
     * currentRoom attribute, an instance from the Room class.
     * The currentRoom variable is what room the player is in at the moment in
     * game.
     */
    private Room currentRoom;

    /**
     * The name of the character
     */
    private String name;

    /**
     * A constructor for a character
     *
     * @param name The name of the character
     */
    public Character(String name) {
        this.name = name;
    }

    /**
     * This method checks if the command has a second word
     *
     * @param command
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null && nextRoom.isLocked()) {
            return;
        }

        if (nextRoom == null) {
            return;
        }

        currentRoom = nextRoom;
    }

    /**
     * Sets the current room
     *
     * @param room
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Get the current room
     *
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Default pickupItems method
     *
     * @param command
     * @return
     */
    public boolean pickupItems(Command command) {
        return false;
    }

    /**
     * Serialize the character object to xml
     *
     * @param Document doc
     * @return xml element
     */
    @Override
    public Element serialize(Document doc) {
        String[] namespace = this.getClass().getName().split("\\.");
        String name = namespace[namespace.length - 1];
        Element character = doc.createElement(name.toLowerCase());

        Element room = doc.createElement("room");
        room.appendChild(doc.createTextNode(this.getCurrentRoom().getName()));
        character.appendChild(room);

        return character;
    }

    /**
     * This method loads the characters
     *
     * @param list
     * @param environment
     */
    @Override
    public void load(NodeList list, Environment environment) {
        Element character = (Element) list.item(0);
        this.setCurrentRoom(
            environment.getRoom(
                character.getElementsByTagName("room").item(0).getTextContent()
            )
        );
    }
}
