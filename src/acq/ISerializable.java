package acq;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

public interface ISerializable {
    /**
     * Serialize object instance to an XML node
     * @param  Document doc
     * @return an element
     */
    public Element serialize(Document doc);
}
