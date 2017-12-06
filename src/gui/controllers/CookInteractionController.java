package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class CookInteractionController extends Controller implements Initializable {


    /**
     * Instance of Stage to be able to close the window.
     */
    private Stage stage;

    /**
     * Button with a "yes" option
     */
    @FXML
    private Button yesButton;

    /**
     * Button with a "no" option
     */
    @FXML
    private Button noButton;

    /**
     * Textarea for the cook interaction
     */
    @FXML
    private TextArea cookTextField;

    /**
     * Button for when you dont want the steak
     */
    @FXML
    private Button okayButton;

    /**
     * Button for when you do want the steak
     */
    @FXML
    private Button thankYouButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Method for the event when "yes"-button is pressed.
     * @param event
     */
    @FXML
    private void onYesButton(ActionEvent event) {
        if (game.getPlayer().isInventoryFull()) {
            cookTextField.setText("Your inventory is full");
            okayButton.setVisible(true);
        } else {
            game.getCook().createSteak(game.getPlayer().getCurrentRoom());
            game.command("pickup", "Steak");


            cookTextField.setText("Here you go, enjoy");
            yesButton.setVisible(false);
            noButton.setVisible(false);
            thankYouButton.setVisible(true);
        }
    }

    /**
     * Method for the event when "no"-button is pressed.
     * @param event
     */
    @FXML
    private void onNoButton(ActionEvent event) {
        cookTextField.setText("Suit yourself.");
        yesButton.setVisible(false);
        noButton.setVisible(false);
        okayButton.setVisible(true);
    }

    /**
     * Method for the nevent when the "okay"-button is pressed.
     * @param event
     */
    @FXML
    private void onOkayButtonAction(ActionEvent event) {
        stage = (Stage) okayButton.getScene().getWindow();
        stage.close();
        resetWindow();
    }

    /**
     * Method for the event when the "thankYou"-button is pressed.
     * @param event
     */
    @FXML
    private void onThankYouButtonAction(ActionEvent event) {
        stage = (Stage) thankYouButton.getScene().getWindow();
        stage.close();
        resetWindow();
    }

    /**
     * Method to reset the cook interaction window.
     */
    private void resetWindow() {
        cookTextField.setText(
            "Hello, don't mind me, i'm just cooking up some Trump steaks over here!\n" +
            "Do you want one ? \n" +
            "President Trump loves his own steaks with some ketchup on!"
        );
        yesButton.setVisible(true);
        noButton.setVisible(true);
        thankYouButton.setVisible(false);
        okayButton.setVisible(false);
    }
}
