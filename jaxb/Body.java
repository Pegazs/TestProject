package TestPackage.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Body")
public class Body {
    @XmlElement(name = "sendPayment", namespace = "wsapi:Payment")
    private SendPayment sendPayment;

    public SendPayment getSendPayment() {
        return sendPayment;
    }
}
