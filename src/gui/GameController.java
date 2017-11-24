/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Rasmus
 */
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
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
     * Button to save game
     */
    @FXML
    private Button saveButton;
    /**
     * Button to load game
     */
    @FXML
    private Button loadButton;
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
        if(game.youLose()) {
            showYouLose();
        }
        if(game.youWin()) {
            showYouWin();
            showHighScore();
        }
    }
    
    /**
     * This method creates an alertbox for when you lose
     * and makes it possible to play again or exit the game
     */
    public void showYouLose() {
        ButtonType playAgain = new ButtonType("Play again", ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType load = new ButtonType("Load saved game", ButtonBar.ButtonData.OTHER);
        Alert youLose = new Alert(AlertType.CONFIRMATION, "You got busted by Trump!", playAgain, close, load);
        youLose.setTitle("You lost");
        youLose.setHeaderText("Well that sucks");
        Optional<ButtonType> result = youLose.showAndWait();
        if(result.get() == playAgain) {
            game.restart();
        }
        if(result.get() == close) {
            System.exit(0);
        }
        if(result.get() == load) {
            game.command("load", "");
        }
        updateGameState();
    }
    
    /**
     * This method creates an alertbox for when you win
     */
    public void showYouWin() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Winner");
        dialog.setHeaderText("Congratulations, you managed to unseat Trump!");
        dialog.setContentText("Please enter your name:");
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            game.getHighscore().add(result.get(), game.getScore());
        }
    }
    
    /**
     * Method to make the save button save the game
     * and trigger an alert window
     * @param event 
     */
    @FXML
    private void onSaveClicked(ActionEvent event) {
        ButtonType saved = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        Alert savedGame = new Alert(AlertType.CONFIRMATION, "", saved);
        savedGame.setTitle("Saved game");
        savedGame.setHeaderText("You succesfully saved the game");
        Optional<ButtonType> result = savedGame.showAndWait();
        game.command("save", "");
    }
    
    /**
     * Method to make the load button load the game 
     * and trigger an alert window 
     * @param event 
     */
    @FXML
    private void onLoadClicked(ActionEvent event) {
        ButtonType loaded = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        Alert loadedGame = new Alert(AlertType.CONFIRMATION, "", loaded);
        loadedGame.setTitle("Loaded game");
        loadedGame.setHeaderText("You succesfully loaded the game");
        Optional<ButtonType> result = loadedGame.showAndWait();
        game.command("load", "");
        updateGameState();
    }
    
    /**
     * This method shows the highscore when the game is won
     */
    public void showHighScore() {
        ButtonType playAgain = new ButtonType("Play again", ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE); 
        Alert highscore = new Alert(AlertType.INFORMATION, game.getHighscore().toString());
        highscore.setTitle("Highscore");
        highscore.setHeaderText("Highscore");
        Optional<ButtonType> result = highscore.showAndWait();
        if(result.get() == playAgain) {
            game.restart();
        }
        if(result.get() == close) {
            System.exit(0);
        }
        updateGameState();
    }
        
    
    
}
