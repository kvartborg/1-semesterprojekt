package acq;

import org.w3c.dom.Document;

import maga.Game;

/*
 * Interface for loadable
 */
public interface ILoadable {
    /**
     * Turn XML into an object intance
     * @param doc
     * @param game
     */
    public void load(Document doc, Game game);
}
