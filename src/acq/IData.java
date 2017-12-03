package acq;

public interface IData {
    /**
     * Serialize and save object to an xml file
     * @param  object
     * @return true if the operation was successful
     */
    public boolean save(ISerializable object);

    /**
     * Load and parse file to the correct object instances
     * @param  object
     * @return true if the file was loaded and parsed correctly
     */
    public boolean load(ILoadable object);
}
