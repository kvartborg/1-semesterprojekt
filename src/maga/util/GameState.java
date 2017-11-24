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
    public static void save(int steps, long startTime, long bonusTime, long saveTime, int points, Player player, Trump trump, Cook cook, Environment environment) {
        try {
            

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element game = doc.createElement("game");
            doc.appendChild(game);

            game.appendChild(
                    GameState.createTextNode(doc, "steps", Integer.toString(steps))
            );

            game.appendChild(
                    GameState.createTextNode(doc, "startTime", Long.toString(startTime))
            );

            game.appendChild(
                    GameState.createTextNode(doc, "bonusTime", Long.toString(bonusTime))
            );
            
            game.appendChild(
                    GameState.createTextNode(doc, "saveTime", Long.toString(saveTime))
            );

            game.appendChild(
                    GameState.createTextNode(doc, "points", Long.toString(points))
            );

            game.appendChild(player.serialize(doc));
            game.appendChild(trump.serialize(doc));
            game.appendChild(cook.serialize(doc));
            game.appendChild(environment.serialize(doc));

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

    public static Element createTextNode(Document doc, String nodeName, String value) {
        Element node = doc.createElement(nodeName);
        node.appendChild(doc.createTextNode(value));
        return node;
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
            game.setSaveTime(Long.parseLong(findElementByName(doc, "saveTime").getTextContent()));

            game.getPlayer().load(doc.getElementsByTagName("player"), game.getEnvironment());
            game.getTrump().load(doc.getElementsByTagName("trump"), game.getEnvironment());
            game.getCook().load(doc.getElementsByTagName("cook"), game.getEnvironment());

            game.getEnvironment().load(doc.getElementsByTagName("room"), game.getEnvironment());

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
    
    public static void loadHighscore(HighScore highscore) {
        try {
            File inputFile = new File ("highScore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize();
            highscore.load(doc.getElementsByTagName("score"), null);
        } catch (Exception e) {}
    }

    public static void saveHighScore (HighScore highScore) {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            doc.appendChild(highScore.serialize(doc));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("highScore.xml"));
            transformer.transform(source, result);
        } catch(Exception e) {
        }
    }

}
