package TestPackage.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sendPayment", namespace = "wsapi:Payment")
public class SendPayment {
    @XmlElement(name="token")
    private String token;
    @XmlElement(name="cardNumber")
    private String cardNumber;
    @XmlElement(name="requestId")
    private String requestId;
    @XmlElement(name="amount")
    private String amount;
    @XmlElement(name="currency")
    private String currency;

    @XmlElement(name="page")
    private int page;

    public String getToken() {
        return token;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPage() {
        return page;
    }
}
