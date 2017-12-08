
package gui.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 */
public class ComputerController extends Controller implements Initializable {
    /**
     * Text area to write a tweet 
     */
    @FXML
    private TextArea writeTweetArea;
    
    /**
     * Text area for tweets 
     */
    @FXML
    private TextArea tweetedArea;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Method for when you use the computer.
     * @param event
     */
    @FXML
    private void onTweetClicked(ActionEvent event) {
        game.getPlayer().tweeted();
        tweetedArea.setText(writeTweetArea.getText());
        writeTweetArea.clear();
        showTweet();
    }

    /**
     * Method to generate a random tweet in the computer window.
     * @param event
     */
    @FXML
    private void onRandomTweetClicked(ActionEvent event) {
        game.getPlayer().tweeted();
        String[] tweets = {
            "I want everyone to know that I am stepping down as president, "
                + "\nand that I secretly love Hillary and Bernie!",
            "I want to apologize to all the reporters I have accused of reporting, "
                + "\n“FAKE NEWS”. They are great! What they do, has a yuuuge importance "
                + "\nto the world!”",
            "I have a secret love affair with Vladimir Putin, "
                + "\nhe gives me the kind of love that I always failed to get from my father!",
            "“I… am… a….. DEMOCRAT! #FuckNRA”"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(tweets.length - 1);
        tweetedArea.setText(tweets[randomIndex]);
        showTweet();
    }
    
     /**
     * Creates an alert box when you tweet 
     */
    public void showTweet() {
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Alert youTweeted = new Alert(Alert.AlertType.INFORMATION, "", ok);
        youTweeted.setTitle("Tweet successful");
        youTweeted.setHeaderText("You have now sent a tweet of redemtpion! "
                + "\nHurry in to the press briefing room!");
        Optional<ButtonType> result = youTweeted.showAndWait();
        
    }
}
