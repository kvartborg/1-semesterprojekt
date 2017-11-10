/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.highscore;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import maga.util.Console;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ander
 */
public class HighScore {
    
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
     * This method makes our highScore into an XML file. 
     */
    public void toXml() {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.newDocument(); 
            
            Element highscore = doc.createElement("highscore");
            doc.appendChild(highscore); 
            
            for (Score score : highScore) {
                Element scoreNode = doc.createElement("score"); 
                scoreNode.setAttribute("name", score.getName());
                scoreNode.setAttribute("score", Integer.toString(score.getScore()));
                highscore.appendChild(scoreNode);
                
            }
            
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
    /**
     * This method loads our highscores from the xml file.
     */
    public void loadXml() {
        try {
            File inputFile = new File ("highScore.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("score");
            for (int i = 0; i <nList.getLength(); i++) {
                Node nNode = nList.item(i);
                
                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    this.add(
                        eElement.getAttribute("name"), 
                        Integer.parseInt(eElement.getAttribute("score"))
                    );
                }
            }
        } catch (Exception e) {}
    }
    /**
     * This method prints the highscores.
     */
    public void printHighScore() {
        System.out.println("\nHighscores:");
        System.out.println(this.toString());
    }
}
