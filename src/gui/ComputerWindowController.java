/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author Kasper
 */
public class ComputerWindowController implements Initializable {
    private IGame game;
    @FXML
    private TextArea writeTweetArea;
    @FXML
    private TextArea tweetedArea;
        
    /**
     * Injects the game into the controller
     * @param game
     */
    public void injectGame(IGame game) {
        this.game = game; 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onTweetClicked(ActionEvent event) {
        game.getPlayer().tweeted();
        tweetedArea.setText(writeTweetArea.getText());
        writeTweetArea.clear();
        
    }

    @FXML
    private void onRandomTweetClicked(ActionEvent event) {
        game.getPlayer().tweeted();
        String[] tweets = {
            "I want everyone to know that I am stepping \ndown as president, "
                + "\nand that I secretly love Hillary and Bernie!",
            "I want to apologize to all the reporters I have accused of \nreporting “FAKE NEWS”. They are great! "
                + "\nWhat they do have a yuuuge importance to the world!”",
            "I have a secret love affair with Vladimir Putin, "
                + "\nhe gives me the kind of love that I always failed \nto get from my father!",
            "“I… am… a….. DEMOCRAT! #FuckNRA”"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(tweets.length - 1);
        tweetedArea.setText(tweets[randomIndex]);        
    }
}
