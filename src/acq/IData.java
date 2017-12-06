package acq;

public interface IData {
    /**
     * Serialize and save object to an xml file
     * @param  name of the written file
     * @param  object
     * @return true if the operation was successful
     */
    public boolean save(String name, ISerializable object);

    /**
     * Load and parse file to the correct object instances
     * @param  name of the written file
     * @param  object
     * @return true if the file was loaded and parsed correctly
     */
    public boolean load(String name, ILoadable object);
}
