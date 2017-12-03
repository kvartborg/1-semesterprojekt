package acq;

import maga.environment.Environment;
import org.w3c.dom.NodeList;

/*
 * Interface for loadable
 */
public interface ILoadable {
    /**
     * Turn XML into an object intance
     * @param NodeList    list
     * @param Environment environment
     */
    public void load(NodeList list, Environment environment);
}
