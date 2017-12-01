package maga.item;

import maga.character.Player;

public class NuclearFootball extends Item{

    /**
     * Constructor for the Item NuclearFootball
     */
    public NuclearFootball() {
        super("NuclearFootball");
    }

    /**
     * This method overrides the method use in Item, and exits the game when used.
     * @param player 
     */
    @Override
    public void use(Player player) {
        System.exit(0);
    }
}
