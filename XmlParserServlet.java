package TestPackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XmlParserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.getWriter().println("<form name=\"xmlForm\" method=\"post\" action=\"xmlServlet\">\n" +
                "<textarea name=\"xml\" rows=\"30\" cols=\"100\" ></textarea> <br/>\n" +
                "<input type=\"submit\" value=\"Submit\" />\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


    }

}
