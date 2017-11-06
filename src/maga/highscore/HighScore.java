/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.highscore;

import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
     * @return 
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
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult); 
        } catch(Exception e) {
            
        }
    }
    
}
