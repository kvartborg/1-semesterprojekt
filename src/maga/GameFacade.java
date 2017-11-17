/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga;

import acq.IGame;
import acq.IData;

/**
 *
 * @author mikkellarsen
 */
public class GameFacade implements IGame {

    private IData data;
    private final Game game = new Game();

    @Override
    public void injectData(IData data) {
        this.data = data;
    }

    @Override
    public void command(String command, String argument) {
        game.processCommand(game.getParser().createCommand(command, argument));
    }

    @Override
    public void play() {
        game.play();
    }
}
