package maga.item;
import maga.character.Player;

public class Steak extends Item {
    
    /**
     * Creating a variable of the type Ketchup
     */
    private boolean ketchup = false;

    /**
     * Constructor for the object Steak
     */
    public Steak(){
        super("Steak");
    }
    
    /**
     * Method checks if the Steak has ketchup on it
     * @return boolean
     */
    public boolean hasKetchup(){
        return this.ketchup;
    }
    
    /**
     * Creates a "use" method for the "Steak" item. The method searches for
     * a "Ketchup" item, and can't be used if there isn't one in the inventory.
     * @param player 
     */
    @Override
    public void use(Player player) {
        for(Item item : player.getItems()) {
            if(item.getName().equals("Ketchup")){
                this.ketchup = true;
                this.setName("Steak-with-ketchup");
                System.out.println("The steak now has ketchup on it.");
                return;
            } 
        }
        System.out.println("You need ketchup to use the steak.");
    }
}