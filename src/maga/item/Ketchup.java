package maga.item;

import maga.character.Player;

public class Ketchup extends Item {

    /**
     * Constructor of the class Ketchup
     */
    public Ketchup() {
        super("Ketchup");
    }

    /**
     * Creates a "use" method for the "Ketchup" item. The method searches for
     * a "steak" item in inventory, and can't be used without it.
     * @param player
     */
    @Override
    public void use(Player player) {
        for(Item item : player.getItems()) {
            if(item.getName().equals("Steak")){
                item.use(player);
                return;
            }
        }
    }
}
