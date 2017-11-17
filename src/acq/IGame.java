/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

/**
 * Interface for game
 */
public interface IGame {

    public void injectData(IData data);

    public void command(String command, String argument);

    public void play();
}
