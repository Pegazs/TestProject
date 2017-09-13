package TestPackage.jaxb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope")
public class Envelope {
    private final static Logger log = LogManager.getLogger(Envelope.class);

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

    public String convertToJsonString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(this);
            log.info("Result of convertToJsonString: " + jsonInString);
            return jsonInString;
        } catch(Exception e) {
            log.error("Error in convertToJsonString: " + e);
        }
        return "";
    }
}
