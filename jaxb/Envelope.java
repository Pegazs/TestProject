package TestPackage.jaxb;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope")
public class Envelope {
    @XmlElement(name = "Body")
    private Body body;


    public Body getBody() {
        return body;
    }

    public static Envelope convertFromStringXml(String stringToConvert) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(stringToConvert);
        return (Envelope) jaxbUnmarshaller.unmarshal(reader);
    }

    //класс тестирования
    public void consoleTest() {
        System.out.println(this.getBody().getSendPayment().getToken());
        System.out.println(this.getBody().getSendPayment().getCardNumber());
        System.out.println(this.getBody().getSendPayment().getRequestId());
        System.out.println(this.getBody().getSendPayment().getAmount());
        System.out.println(this.getBody().getSendPayment().getCurrency());

        System.out.println(this.getBody().getSendPayment().getPage());
    }
}
