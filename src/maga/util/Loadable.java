package maga.util;

import maga.environment.Environment;
import org.w3c.dom.NodeList;


/*
 * Interface for loadable
 */
public interface Loadable {

    public void load(NodeList list, Environment environment);
}