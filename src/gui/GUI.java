/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import maga.GameFacade;

/**
 *
 * @author Rasmus
 */
public class GUI extends Application {

    private IGame game = new GameFacade();
    GameController gameController;

    /**
     * Method to start the game with gui
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = (Parent) loader.load();

        this.gameController = loader.getController();
        this.gameController.injectGame(this.game);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> this.onKeyPressed(event));
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to make the player move when using the keyboard
     *
     * @param event
     */
    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                game.command("go", "north");
                break;

            case LEFT:
                game.command("go", "west");
                break;

            case RIGHT:
                game.command("go", "east");
                break;

            case DOWN:
                game.command("go", "south");
                break;
        }
        this.gameController.updateGameState();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
