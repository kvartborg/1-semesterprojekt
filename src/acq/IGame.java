/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import maga.character.Cook;
import maga.character.Player;
import maga.character.Trump;

/**
 * Interface for game
 */
public interface IGame {

    public void injectData(IData data);

    public void command(String command, String argument);

    public void play();
    
    public Player getPlayer();
    
    public Trump getTrump();
    
    public Cook getCook();
    
    public int getSteps();
}
