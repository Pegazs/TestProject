package TestPackage.jaxb;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public String convertToJsonString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(this);
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            return jsonInString;
            //TODO: записать в лог
        } catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
