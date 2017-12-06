
package gui.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 */
public class ComputerWindowController extends Controller implements Initializable {
    @FXML
    private TextArea writeTweetArea;
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

    }

    /**
     * Method to generate a random tweet in the computer window.
     * @param event
     */
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
