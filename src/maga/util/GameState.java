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
import maga.highscore.HighScore;
import acq.IGame;

public class GameState {

    /**
     * Creates a list of available items
     */
    public static List<Item> items = new ArrayList<Item>();

    /**
     * Saves the game in an XML file
     * @param steps
     * @param startTime
     * @param bonusTime
     * @param points
     * @param player
     * @param trump
     * @param cook
     * @param environment
     */
    public static void save(Game game) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            game.serialize(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/gameState.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

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
     * Loads the game from XML file
     * @param game
     */
    public static void load(IGame game) {
        // GameState.resetGame(game);
        // try {
        //     File file = new File("gameState.xml");
        //     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        //     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //     Document doc = dBuilder.parse(file);
        //     doc.getDocumentElement().normalize();
        //     game.load(doc, game);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
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
     * Resets the game
     * @param game
     */
    public static void resetGame(IGame game) {
        GameState.items.add(new Steak());
        for (Item item : game.getPlayer().getItems()) {
            GameState.items.add(item);
        }

        for (Room room : game.getEnvironment().getRooms().values()) {
            for (Item item : room.getItems()) {
                GameState.items.add(item);
            }
        }
    }

    /**
     * This method makes it possible to search the items arraylist on in
     * GameState
     * @param name
     * @return Item
     */
    public static Item findItem(String name) {
        for (Item item : GameState.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Method to load the HighScore.
     * @param highscore
     */
    public static void loadHighscore(HighScore highscore) {
        try {
            File inputFile = new File ("highScore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            highscore.load(doc, null);
        } catch (Exception e) {}
    }

    /**
     * Method to save the HighScore
     * @param highScore
     */
    public static void saveHighScore (HighScore highScore) {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            highScore.serialize(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("highScore.xml"));
            transformer.transform(source, result);
        } catch(Exception e) {
            //e.printStackTrace();
        }
    }
}
