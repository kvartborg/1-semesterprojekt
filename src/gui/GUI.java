
package gui;

import acq.IGame;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import maga.GameFacade;


public class GUI extends Application {
    
    /**
     * Instace of IGame
     */
    private IGame game = new GameFacade();
    
    /**
     * Instance of GameController
     */
    private GameController gameController;
    
    /**
     *Instance of SearchWindowController 
     */
    private SearchWindowController searchWindowController;
    
    /**
     * Instance of InventoryWindowController
     */
    private InventoryWindowController inventoryWindowController;
    
    /**
     * Instance of CookInteractionController
     */
    private CookInteractionController cookInteractionController;
    
    /**
     * Instance of ComputerWindowController
     */
    private ComputerWindowController computerWindowController;
    
    /**
     * Instance of HelpWindowController
     */
    private HelpWindowController helpWindowController;
    
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
     */
    @Override
    public void start(Stage stage) {
        this.loadGameController(stage);
        searchWindowController = (SearchWindowController) this.loadController("SearchWindow.fxml", "Search");
        inventoryWindowController = (InventoryWindowController) this.loadController("InventoryWindow.fxml", "Inventory");
        cookInteractionController = (CookInteractionController) this.loadController("CookInteraction.fxml", "Cook");
        computerWindowController = (ComputerWindowController) this.loadController("ComputerWindow.fxml", "Computer");
        helpWindowController = (HelpWindowController) this.loadController("HelpWindow.fxml", "Help"); 
    }
    
    /**
     * Method to load a controller.
     * @param controllerName
     * @param stageName
     * @return Controller or null
     */
    public Controller loadController(String controllerName, String stageName) {
       try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerName));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) loader.load();

            this.gameController = loader.getController();
            this.gameController.injectGame(this.game);  
            this.gameController.injectGUI(this);

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
           
            case S:
                searchWindowController.addItemsToViewList();
                stages.get("Search").show();
            break;
           
            case I:
                inventoryWindowController.addItemsToViewList();
                stages.get("Inventory").show();
            break;
            
            case T:
                if (game.getCook().getCurrentRoom() == game.getPlayer().getCurrentRoom()) {
                    stages.get("Cook").show();
                }
            break;
            
            case C:
                game.command("call","");
            break;
            
            case W:
                game.command("wait", "");
            break;
       }
      
       this.gameController.updateGameState();
    }

    public static void main(String[] args) {
        launch(args);
    }
}