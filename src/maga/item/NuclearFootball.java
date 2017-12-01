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
        System.out.println("You used the Nuclear Football, and hereby "
                + "bombed North Korea, thus starting WW3. Game lost.");
        System.exit(0); 
    }
}