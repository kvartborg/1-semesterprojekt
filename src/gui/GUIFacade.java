
package gui;

import acq.IGUI;
import acq.IGame;
import javafx.stage.Stage;

/**
 * Gui facade which implements the inferface for GUI
 */
public class GUIFacade implements IGUI {

    /**
     * Instance of IGame
     */
    private IGame game;

    /**
     * A method to inject game
     *
     * @param game
     */
    @Override
    public void injectGame(IGame game) {
        this.game = game;
    }

    /**
     * A method to inject stage
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        new GUI(stage, game);
    }
}
