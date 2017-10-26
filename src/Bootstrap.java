import maga.Game;
import javafx.application.Application;
import javafx.stage.Stage;


public class Bootstrap extends Application {

    /**
     * This method starts the game.
     *
     * It is the main method for the whole project and creates an object
     * game from the class Game. After that it initializes the games beginning.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage) {
        Game game = new Game(stage);
        game.play();
    }
}
