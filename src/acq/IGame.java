
package acq;

import maga.character.Cook;
import maga.character.Player;
import maga.character.Trump;
import maga.highscore.HighScore;
import maga.environment.Environment;
import org.w3c.dom.Document;

/**
 * Interface for game
 */
public interface IGame {

    /**
     * Injects data to Game
     * @param data
     */
    public void injectData(IData data);

    /**
     * A method for the game commands
     * @param command
     * @param argument
     */
    public void command(String command, String argument);

    /**
     * A getter for player
     * @return player
     */
    public Player getPlayer();

    /**
     * A getter for Trump
     * @return Trump
     */
    public Trump getTrump();

    /**
     * A getter for Cook
     * @return Cook
     */
    public Cook getCook();

    /**
     * A getter for steps
     * @return Steps
     */
    public int getSteps();

    /**
     * A method to check the lose condition
     * @return boolean
     */
    public boolean youLose();

    /**
     * A method to check the winning condition
     * @return boolean
     */
    public boolean youWin();

    /**
     * A method to restart the game
     */
    public void restart();

    /**
     * A getter for highscore
     * @return Highscore
     */
    public HighScore getHighscore();

    /**
     * A getter for the individual score
     * @return integer
     */
    public int getScore();

    /**
     * A getter for the enviroment instance
     * @return environment
     */
    public Environment getEnvironment();

    /**
     * A getter for the enviroment instance
     * @param name
     * @param score
     */
    public void addScore(String name, int score);
}
