
package gui;

import acq.IGame;


public abstract class Controller {
    /**
     * Instance of IGame
     */
    protected IGame game;
    
    /**
     * Intance of GUI
     */
    protected GUI gui;
    
    /**
     * Method to inject GUI
     * @param gui 
     */
    public void injectGUI(GUI gui) {
        this.gui = gui;
    }
    
    /**
     * Method to inject Igame
     * @param game 
     */
    public void injectGame(IGame game) {
        this.game = game;
    }
    
}
