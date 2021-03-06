package maga.highscore;

import java.util.ArrayList;
import java.util.Collections;
import maga.Game;
import acq.ILoadable;
import acq.ISerializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HighScore implements ISerializable, ILoadable {

    /**
     * An ArrayList to store the players highScore.
     */
     ArrayList<Score> highScore = new ArrayList();


    /**
     * An add method to add a name and a score to the ArrayList.
     * @param name
     * @param score
     */
    public void add(String name, int score) {
        highScore.add(new Score(name, score));
        this.sort();
    }

    /**
     * A method to sort the highScore so the one with the highest score is first.
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(highScore, comparator);
    }

    /**
     * A method to return our highScore as an string.
     * @return string
     */
    @Override
    public String toString() {
        String text = "";
        for (Score score : highScore) {
            text += score.getName() + ": " + score.getScore() + "\n";
        }
        return text;
    }

    /**
     * Serialize highscore to xml
     * @param doc
     * @return xml document
     */
    @Override
    public Document serialize(Document doc) {
        Element root = doc.createElement("highscore");

        for (Score score : highScore) {
            Element scoreNode = doc.createElement("score");
            scoreNode.setAttribute("name", score.getName());
            scoreNode.setAttribute("score", Integer.toString(score.getScore()));
            root.appendChild(scoreNode);
        }

        doc.appendChild(root);

        return doc;
    }

    /**
     * This method loads our highscores from the xml file.
     * @param doc
     * @param game
     */
    @Override
    public void load(Document doc, Game game) {
        NodeList list = doc.getElementsByTagName("score");

        for (int i = 0; i < list.getLength(); i++) {
            Node nNode = list.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                this.add(
                    eElement.getAttribute("name"),
                    Integer.parseInt(eElement.getAttribute("score"))
                );
            }
        }
    }
}
