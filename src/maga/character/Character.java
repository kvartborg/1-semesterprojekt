
package maga.character;

import maga.command.Command;
import maga.environment.Environment;
import maga.environment.Room;
import acq.ISerializable;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import acq.IGame;
import acq.ILoadable;
import org.w3c.dom.Document;


public abstract class Character implements ISerializable, ILoadable {

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
     * Get name of the class
     * @return the instanciated class name
     */
    public String getClassName() {
        String[] namespace = this.getClass().getName().split("\\.");
        return namespace[namespace.length - 1];
    }

    /**
     * Serialize the character object to xml
     *
     * @param Document doc
     * @return xml document
     */
    @Override
    public Document serialize(Document doc) {
        Element character = doc.createElement(this.getClassName().toLowerCase());

        Element room = doc.createElement("room");
        room.appendChild(doc.createTextNode(this.getCurrentRoom().getName()));
        character.appendChild(room);

        Element game = (Element) doc.getDocumentElement();
        game.appendChild(character);

        return doc;
    }

    /**
     * This method loads the characters
     *
     * @param doc
     * @param game
     */
    @Override
    public void load(Document doc, IGame game) {
        Element character = (Element) doc.getElementsByTagName(
            this.getClassName().toLowerCase()
        ).item(0);

        this.setCurrentRoom(
            game.getEnvironment().getRoom(
                character.getElementsByTagName("room").item(0).getTextContent()
            )
        );
    }
}
