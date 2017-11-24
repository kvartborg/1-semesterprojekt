/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;

/**
 *
 * @author Rasmus
 */
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
