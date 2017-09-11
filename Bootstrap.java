package TestPackage;

import java.io.*;
import java.util.Properties;

public class Bootstrap {
    public static void main(String[] args) {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (IOException exception) {
            System.err.println("IOException");
        }
        String jettyPort = property.getProperty("http.port");
        String destinationAddress = property.getProperty("tcp.dest.addr");
        String destinationPort = property.getProperty("tcp.dest.port");

    }
}
