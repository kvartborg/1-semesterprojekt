package maga.util;

import java.io.File;
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

public class GameState {

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
            trumpE.appendChild(doc.createTextNode(trump.getCurrentRoom().getName()));
            game.appendChild(trumpE);

            Element cookE = doc.createElement("cook");
            cookE.appendChild(doc.createTextNode(cook.getCurrentRoom().getName()));
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

    public void load() {

    }
}
