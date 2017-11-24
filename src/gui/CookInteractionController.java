/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import com.sun.media.jfxmedia.MediaManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Rasmus
 */
public class CookInteractionController extends Controller implements Initializable {
    
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
        } else {    
            game.getCook().createSteak(game.getPlayer().getCurrentRoom());
            game.command("pickup", "Steak");
            
            System.out.println(game.getPlayer().getNameOfItems());
            cookTextField.setText("Here you go, enjoy");
        }
    }
    
    /**
     * Method for the event when "no"-button is pressed.
     * @param event 
     */
    @FXML
    private void onNoButton(ActionEvent event) {
        cookTextField.setText("Suit yourself.");
    }    
}
