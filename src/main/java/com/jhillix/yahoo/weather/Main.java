package com.jhillix.yahoo.weather;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import java.io.InputStream;


/**
 * Orchestration of the entire process.
 *
 * @author jhillix
 */
public class Main {

    private static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        // Configure Log4J.
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));

        // Default to 96022.
        String zipcode = "96022";

        // If we're passed a zipcode use it.
        if (args.length > 0) {
            zipcode = args[0];
        }

        try {
            // Retrieve the data.
            InputStream inputStream = new YahooRSS().forecast(Integer.parseInt(zipcode));

            // Parse the data.
            Weather weather = new YahooRSSParser().parse(inputStream);

            // Format the data.
            System.out.println(new WeatherFormatter().format(weather));
        } catch (IOException ex) {
            LOG.error(ex.getStackTrace());
        }
    }
}
