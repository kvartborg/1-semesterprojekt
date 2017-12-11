
import acq.IData;
import acq.IGUI;
import acq.IGame;
import data.DataMediator;
import maga.GameFacade;
import gui.GUIFacade;
import javafx.application.Application;
import javafx.stage.Stage;

public class Bootstrap extends Application {
    /**
     * This method starts the game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Sets up the application layers. 
     * @param stage 
     */
    @Override
    public void start(Stage stage) {
        IData data = new DataMediator();
        IGame game = new GameFacade();
        IGUI gui = new GUIFacade();
        game.injectData(data);
        gui.injectGame(game);
        gui.start(stage);
    }
}
