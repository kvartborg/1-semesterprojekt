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
    protected IGame game;
    protected GUI gui;
    
    public void injectGUI(GUI gui) {
        this.gui = gui;
    }
    
    public void injectGame(IGame game) {
        this.game = game;
    }
    
}
