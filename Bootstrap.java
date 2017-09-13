package TestPackage;

import java.io.*;
import TestPackage.http.XmlParserServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import java.util.Properties;


public class Bootstrap {
    public static void main(String[] args) throws Exception {
        final Logger log = LogManager.getLogger(Bootstrap.class);
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (IOException e) {
            log.error("Error in Bootstrap (property file): " + e);
        }
        int jettyPort = Integer.parseInt(property.getProperty("http.port"));
        String destinationAddress = property.getProperty("tcp.dest.addr");
        int destinationPort = Integer.parseInt(property.getProperty("tcp.dest.port"));

        Server server = new Server(jettyPort);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        XmlParserServlet servletMain = new XmlParserServlet(destinationAddress, destinationPort);
        ServletHolder servletHolder = new ServletHolder(servletMain);
        handler.addServlet(servletHolder, "/");
        server.start();
    }
}
