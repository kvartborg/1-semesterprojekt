
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
     * It is the main method for the whole project and creates an object game
     * from the class Game. After that it initializes the games beginning.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage) {
        IData data = new DataMediator();
        IGame game = new GameFacade();
        IGUI gui = new GUIFacade();
        game.injectData(data);
        gui.injectGame(game);
        gui.start(stage);
    }
}
