
package acq;

import javafx.stage.Stage;

/**
 * Interface for GUI
 */
public interface IGUI {

    /**
     * Injects Game to GUI
     * @param game
     */
    public void injectGame(IGame game);

    /**
     * Start gui application
     * @param stage
     */
    public void start(Stage stage);
}
