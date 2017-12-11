package maga.util;

import java.util.ArrayList;
import java.util.List;
import maga.item.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import maga.environment.Room;
import maga.Game;
import maga.item.Steak;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Helper {

    /**
     * Creates a list of available items
     */
    public static List<Item> items = new ArrayList<Item>();


    /**
     * method to create text node
     * @param doc
     * @param nodeName
     * @param value
     * @return Element
     */
    public static Element createTextNode(Document doc, String nodeName, String value) {
        Element node = doc.createElement(nodeName);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


    /**
     * This method finds an item in items ArrayList
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
     * collect all existing items within the game
     * @param game
     */
    public static void collectItems(Game game) {
        Helper.items.add(new Steak());
        for (Item item : game.getPlayer().getItems()) {
            Helper.items.add(item);
        }

        for (Room room : game.getEnvironment().getRooms().values()) {
            for (Item item : room.getItems()) {
                Helper.items.add(item);
            }
        }
    }

    /**
     * This method makes it possible to search the items arraylist on in
     * Helper
     * @param name
     * @return Item
     */
    public static Item findItem(String name) {
        for (Item item : Helper.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
