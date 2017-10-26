/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.item;

import maga.character.Player;

/**
 *
 * @author ander
 */
public class NuclearFootball extends Item{

    /**
     * Creating a constructor of the class NuclearFootball extending Item
     */
    public NuclearFootball() {
        super("NuclearFootball");
    }
    /**
     * This method uses the NuclearFootball item and exits the game when used.
     * @param player
     */
    @Override
    public void use(Player player) {
        System.out.println("You used the Nuclear Football, and hereby "
                + "bombed North Korea, thus starting WW3. Game lost.");
        System.exit(0);
    }
}
