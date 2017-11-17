/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGUI;
import acq.IGame;

/**
 * Gui facade which implements the inferface for GUI
 *
 */
public class GUIFacade implements IGUI {

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
