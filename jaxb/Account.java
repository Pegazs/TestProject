package TestPackage.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "sendPayment", namespace = "wsapi:Utils")
public class Account {
    @XmlAttribute(name = "type")
    private String type;
    @XmlValue
    private String value;

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
