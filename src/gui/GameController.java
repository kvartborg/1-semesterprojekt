/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import static javafx.util.Duration.millis;
import maga.Game;

/**
 *
 * @author Rasmus
 */
public class GameController implements Initializable {
    /**
     * Making an instance of IGame
     */
    private IGame game;
    /**
     * Label to count steps
     */
    @FXML
    private Label stepsLabel;
    /**
     * Label to see what items player has in left hand
     */
    @FXML
    private Label leftHandLabel;
    /**
     * Label to see what items player has in right hand
     */
    @FXML
    private Label rightHandLabel;
    /**
     * Button to call the help command 
     */
    @FXML
    private Button helpButton;
    /**
     * Image of our player character
     */
    @FXML
    private ImageView player;
    /**
     * Image of our cook 
     */
    @FXML
    private ImageView cook;
    /**
     * Image of trump 
     */
    @FXML
    private ImageView trump;
    /**
     * Method to initialize
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * Method to make the button do something 
     * @param event 
     */
    @FXML
    private void onHelpClicked(ActionEvent event) {
    }
     
    /**
     * Method to inject the game into the gui 
     * @param game 
     */
    public void injectGame(IGame game) {
        this.game = game; 
    }
    
    /**
     * Method to update the current state of the game 
     */
    public void updateGameState() {
        GridPane.setConstraints(player, game.getPlayer().getCurrentRoom().getX(), game.getPlayer().getCurrentRoom().getY());
        GridPane.setConstraints(trump, game.getTrump().getCurrentRoom().getX(), game.getTrump().getCurrentRoom().getY());
        stepsLabel.setText(Integer.toString(game.getSteps()));
    }
    
}
