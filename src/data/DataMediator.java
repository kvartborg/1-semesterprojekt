
package data;

import acq.IData;
import acq.ILoadable;
import acq.ISerializable;

/**
 * DataFacade which implements the interface for Data
 *
 */
public class DataMediator implements IData {
    /**
     * Serialize and save object to an xml file
     * @param  object
     * @return true if the operation was successful
     */
    @Override
    public boolean save(ISerializable object) {
        return true;
    }

    /**
     * Load and parse file to the correct object instances
     * @param  object
     * @return true if the file was loaded and parsed correctly
     */
    @Override
    public boolean load(ILoadable object) {
        return true;
    }



}
