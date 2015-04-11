package com.jhillix.yahoo.weather;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Opens a connection to the Yahoo! weather RSS feed with specified zipcode.
 *
 * @author jhillix
 */
public class YahooRSS {

    private static Logger LOG = Logger.getLogger(YahooRSS.class);

    private URL url;

    public YahooRSS() {}

    /**
     * Establishes the connection to Yahoo! Weather RSS and returns the stream.
     *
     * @param zipcode  weather information will be returned for this zipcode
     * @return         InputStream of the RSS data
     * @throws IOException
     */
    public InputStream forecast(final int zipcode) throws IOException {
        LOG.info("Gathering weather data.");

        try {
            url = new URL("http://weather.yahooapis.com/forecastrss?p=" + zipcode);

        } catch (MalformedURLException ex) {
            LOG.warn(ex.getStackTrace());
        }

        return url.openStream();
    }
}
