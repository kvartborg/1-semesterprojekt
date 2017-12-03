package acq;

public interface IData {
    /**
     * Save a serializable object to a file
     * @param  Serializable object
     * @return true if the object was successfully save
     */
    public boolean save(ISerializable object);

    /**
     * Load a xml file and turn all nodes into the correct object instances
     * @param  Loadable object
     * @return true if the xml files was loaded correctly
     */
    public boolean load(ILoadable object);

}
