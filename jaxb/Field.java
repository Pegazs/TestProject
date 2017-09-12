package TestPackage.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sendPayment")
public class Field {
    @XmlAttribute(name="id")
    private int id;
    @XmlAttribute(name="value")
    private String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
