package TestPackage.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import TestPackage.jaxb.Envelope;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.xml.bind.DatatypeConverter;

public class XmlParserServlet extends HttpServlet {
    private final static Logger log = LogManager.getLogger(XmlParserServlet.class);

    private String destinationAddress;
    private int destinationPort;

    private XmlParserServlet() {}

    public XmlParserServlet(String destinationAddressForTcp, int destinationPortForTcp) {
        destinationAddress = destinationAddressForTcp;
        destinationPort = destinationPortForTcp;
    }

    private String SendJsonStringOverTcp(String jsonString) throws IOException {
        byte[] header = ByteBuffer.allocate(4).putInt(0xFFBBCCDD).array();
        byte[] length = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(jsonString.length()).array();
        byte[] json = jsonString.getBytes("UTF-16LE");
        String hexString = DatatypeConverter.printHexBinary(header) + DatatypeConverter.printHexBinary(length) + DatatypeConverter.printHexBinary(json);
        log.info("HEX string for sending from SendJsonStringOverTcp: " + hexString);

        try {
            Socket socket = new Socket(destinationAddress, destinationPort);
            socket.getOutputStream().write(header);
            socket.getOutputStream().write(length);
            socket.getOutputStream().write(json);
            socket.getOutputStream().flush();
            return "Sending was successful";
        }  catch(Exception e) {
            log.error("Error in SendJsonStringOverTcp: " + e);
            return "Failed to send: " + e;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<form name=\"xmlForm\" method=\"post\" action=\"xmlServletResult\">\n" +
                "<textarea name=\"xml\" rows=\"30\" cols=\"100\" ></textarea> <br/>\n" +
                "<input type=\"submit\" value=\"Submit\" />\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xmlFromForm = request.getParameter("xml");
        try {
            Envelope envelopeObject = Envelope.convertFromStringXml(xmlFromForm);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String jsonString = envelopeObject.convertToJsonString();
            String htmlResponse = "Сконвертировано:<br>\n"+jsonString;
            htmlResponse += ("<br><br>" + SendJsonStringOverTcp(jsonString));
            writer.println(htmlResponse);
        } catch(Exception e) {
            log.error("Error in doPost: " + e);
        }

    }

}