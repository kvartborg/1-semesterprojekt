package acq;

import org.w3c.dom.Document;

/*
 * Interface for loadable
 */
public interface ILoadable {
    /**
     * Turn XML into an object intance
     * @param doc
     * @param game
     */
    public void load(Document doc, IGame game);
}
