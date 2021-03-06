
package gui;

import acq.IGame;
import java.util.HashMap;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import gui.controllers.*;


public class GUI {

    /**
     * Instace of IGame
     */
    private IGame game;

    /**
     * Instance of GameController
     */
    private GameController gameController;

    /**
     *Instance of SearchController
     */
    private SearchController searchController;

    /**
     * Instance of InventoryController
     */
    private InventoryController inventoryController;

    /**
     * Instance of CookInteractionController
     */
    private CookInteractionController cookInteractionController;

    /**
     * Instance of ComputerController
     */
    private ComputerController computerController;

    /**
     * Instance of HelpController
     */
    private HelpController helpController;

    /**
     * HashMap with stages
     */
    private HashMap<String, Stage> stages = new HashMap<>();

    /**
     * Accessor method for HashMap with stages.
     * @return stages
     */
    public HashMap<String, Stage> getStages() {
        return stages;
    }

    /**
     * Method to start the game with gui
     * @param stage
     * @param game
     */
    public GUI (Stage stage, IGame game) {
        this.game = game;
        this.loadGameController(stage);
        searchController = (SearchController) this.loadController("Search.fxml", "Search");
        inventoryController = (InventoryController) this.loadController("Inventory.fxml", "Inventory");
        cookInteractionController = (CookInteractionController) this.loadController("CookInteraction.fxml", "Cook");
        computerController = (ComputerController) this.loadController("Computer.fxml", "Computer");
        helpController = (HelpController) this.loadController("Help.fxml", "Help");
    }

    /**
     * Method to load a controller.
     * @param controllerName
     * @param stageName
     * @return Controller or null
     */
    public Controller loadController(String controllerName, String stageName) {
       try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "views/" + controllerName
            ));
            Parent root = (Parent) loader.load();

            Controller controller = loader.getController();
            controller.injectGame(this.game);
            controller.injectGUI(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setMinWidth(400);
            stage.setMinHeight(300);
            stage.setScene(scene);
            stages.put(stageName, stage);
            stage.setTitle(stageName);
            return controller;
        } catch(Exception e){}
       return null;
    }

    /**
     * Loads the game controller stage
     * @param stage
     */
    public void loadGameController(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Game.fxml"));
            Parent root = (Parent) loader.load();

            this.gameController = loader.getController();
            this.gameController.injectGame(this.game);
            this.gameController.injectGUI(this);
            this.gameMenu();

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> this.onKeyPressed(event));
            stage.setMinWidth(800);
            stage.setMinHeight(600);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){}
    }

    /**
     * Method to make the player move when using the keyboard
     * @param event
     */
    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                goNextRoom("north");
                break;

            case LEFT:
                goNextRoom("west");
                break;

            case RIGHT:
                goNextRoom("east");
                break;

            case DOWN:
                goNextRoom("south");
                break;

            case S:
                searchController.addItemsToViewList();
                stages.get("Search").show();
                break;

            case I:
                inventoryController.addItemsToViewList();
                stages.get("Inventory").show();
                break;

            case T:
                if (game.getCook().getCurrentRoom() == game.getPlayer().getCurrentRoom()) {
                    stages.get("Cook").show();
                }
                break;

            case C:
                gameController.showCallTrump();
                game.command("call","");
                break;

            case W:
                game.command("wait", "");
                break;
       }

       this.gameController.updateGameState();
    }

    /**
     * Method to load a game menu before entering the actual game.
     */
    public void gameMenu() {
        ButtonType newGame = new ButtonType("New game", ButtonBar.ButtonData.OTHER);
        ButtonType load = new ButtonType("Load saved game", ButtonBar.ButtonData.OTHER);
        ButtonType highScore = new ButtonType("Highscore", ButtonBar.ButtonData.OTHER);
        ButtonType close = new ButtonType("Close game", ButtonBar.ButtonData.OTHER);
        Alert gameMenu = new Alert(Alert.AlertType.INFORMATION, "", newGame, load, highScore, close);
        gameMenu.setTitle("Game menu");
        gameMenu.setHeaderText("Game menu");
        Optional<ButtonType> result = gameMenu.showAndWait();
        if (result.get() == load) {
            game.command("load", "");
        }
        if (result.get() == highScore) {
            this.gameController.highScoreAlert();
        }
        if (result.get() == close) {
            System.exit(0);
        }
        if (result.get() == newGame) {
            game.restart();
        }
        gameController.updateGameState();
    }

    /**
     * Method that checks if the next room is locked
     * @param direction
     */
    public void goNextRoom(String direction){
        if (
            game.getPlayer().getCurrentRoom().getExit(direction) != null &&
            game.getPlayer().getCurrentRoom().getExit(direction).isLocked()
        ) {
            gameController.showLockedRoom();
        }
        game.command("go", direction);
    }
}
