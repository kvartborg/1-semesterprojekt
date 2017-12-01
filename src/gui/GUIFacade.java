
package gui;

import acq.IGUI;
import acq.IGame;

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
}
