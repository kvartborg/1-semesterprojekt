package maga.util;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

public interface Serializable {
    public Element serialize(Document doc);
}
