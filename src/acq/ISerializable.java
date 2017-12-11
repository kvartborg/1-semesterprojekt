package acq;

import org.w3c.dom.Document;

public interface ISerializable {
    /**
     * Serialize object instance to an XML node
     * @param  doc
     * @return an element
     */
    public Document serialize(Document doc);
}
