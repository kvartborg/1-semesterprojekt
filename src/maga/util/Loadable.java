/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.util;

import maga.environment.Environment;
import org.w3c.dom.NodeList;


/*
 * Interface for loadable
 */
public interface Loadable {

    public void load(NodeList list, Environment environment);

}
