
import acq.IData;
import acq.IGUI;
import acq.IGame;
import data.DataFacade;
import maga.GameFacade;
import gui.GUIFacade;

public class Bootstrap {

    /**
     * This method starts the game.
     *
     * It is the main method for the whole project and creates an object game
     * from the class Game. After that it initializes the games beginning.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IData data = new DataFacade();
        IGame game = new GameFacade();
        IGUI gui = new GUIFacade();
        game.injectData(data);
        gui.injectGame(game);
        game.play();

    }

}
