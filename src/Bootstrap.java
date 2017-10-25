import maga.Game;

public class Bootstrap {

    /** 
     * This method starts the game. 
     * 
     * It is the main method for the whole project and creates an object
     * game from the class Game. After that it initializes the games beginning.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

}
