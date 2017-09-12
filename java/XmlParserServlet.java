package TestPackage.java;



import TestPackage.jaxb.Envelope;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XmlParserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("<form name=\"xmlForm\" method=\"post\" action=\"xmlServletResult\">\n" +
                "<textarea name=\"xml\" rows=\"30\" cols=\"100\" ></textarea> <br/>\n" +
                "<input type=\"submit\" value=\"Submit\" />\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xmlFromForm = request.getParameter("xml");
        try {

            Envelope envelopeObject = Envelope.convertFromStringXml(xmlFromForm);
            envelopeObject.consoleTest();

        } catch(Exception e) {
            System.out.println(e);
            //логи
        }

    }

}