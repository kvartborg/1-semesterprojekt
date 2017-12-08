
package maga;

import acq.IGame;
import acq.IData;
import maga.character.Cook;
import maga.character.Player;
import maga.character.Trump;
import maga.highscore.HighScore;
import maga.environment.Environment;
import org.w3c.dom.Document;

/**
 * Facade for Game which implements the interface for game
 */
public class GameFacade implements IGame {

    /**
     * Instance of IData
     */
    private IData data;

    /**
     * Instance of Game
     */
    private final Game game = new Game();

    /**
     * A method that injects Data
     * @param data
     */
    @Override
    public void injectData(IData data) {
        this.data = data;
        game.injectData(data);
    }

    /**
     * A method which allows us to use commands
     *
     * @param command
     * @param argument
     */
    @Override
    public void command(String command, String argument) {
        game.processCommand(game.getParser().createCommand(command, argument));
    }

    /**
     * This method returns the player
     * @return player
     */
    @Override
    public Player getPlayer() {
        return game.getPlayer();
    }

    /**
     * This method returns Trump
     * @return trump
     */
    @Override
    public Trump getTrump() {
        return game.getTrump();
    }

    /**
     * This method returns the Cook
     * @return cook
     */
    @Override
    public Cook getCook() {
        return game.getCook();
    }

    /**
     * This method returns the steps
     * @return steps
     */
    @Override
    public int getSteps() {
        return game.getSteps();
    }

    /**
     * This method makes it possible to win the game
     * @return boolean
     */
    @Override
    public boolean youWin() {
        return game.youWin();
    }

    /**
     * This method makes it possible to lose the game
     * @return boolean
     */
    @Override
    public boolean youLose() {
        return game.youLose();
    }


    /**
     * This method restarts the game
     */
    @Override
    public void restart() {
        game.restart();
    }

    /**
     * This method returns highscore
     * @return highscore
     */
    @Override
    public HighScore getHighscore() {
        return game.getHighscore();
    }

    /**
     * A method to get score
     * @return score
     */
    @Override
    public int getScore() {
        return game.getScore();
    }

    /**
     * A method to get environment
     * @return instance of environment
     */
    @Override
    public Environment getEnvironment() {
        return game.getEnvironment();
    }

    /**
     * Add score to highscore
     * @param name
     * @param score
     */
    public void addScore(String name, int score) {
        game.addScore(name, score);
    }
}
