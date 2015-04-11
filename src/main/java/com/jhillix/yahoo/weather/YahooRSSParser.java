package com.jhillix.yahoo.weather;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * Parse the RSS XML data that was returned from Yahoo! Weather and put that state in a Weather object.
 *
 * @author jhillix
 */
public class YahooRSSParser {

    private static Logger LOG = Logger.getLogger(YahooRSSParser.class);

    private SAXReader saxReader;

    private Document document;

    public YahooRSSParser() {}

    /**
     * Pipe the RSS stream and return back a Weather object that has been instantiated with the weather data for the previously
     * provided zipcode.
     *
     * @param inputStream  RSS stream
     * @return             Weather object
     */
    public Weather parse(final InputStream inputStream) {
        Weather weather = new Weather();

        LOG.info("Creating XML reader.");
        saxReader = createXMLReader();

        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException ex) {
            LOG.warn(ex.getStackTrace());
        }

        LOG.info("Parsing XML response.");
        weather.setCity(document.valueOf("/rss/channel/yweather:location/@city"));
        weather.setRegion(document.valueOf("/rss/channel/yweather:location/@region"));
        weather.setCountry(document.valueOf("/rss/channel/yweather:location/@country"));
        weather.setCondition(document.valueOf("/rss/channel/item/yweather:condition/@text"));
        weather.setTemp(document.valueOf("/rss/channel/item/yweather:condition/@temp"));
        weather.setChill(document.valueOf("/rss/channel/yweather:wind/@chill"));
        weather.setHumidity(document.valueOf("/rss/channel/yweather:atmosphere/@humidity"));

        return weather;
    }

    /**
     * Instantiates a SAXReader for parsing Yahoo! Weather's RSS feed.
     *
     * @return  SAXReader
     */
    private SAXReader createXMLReader() {
        Map<String, String> namespace = new HashMap<>();

        namespace.put("yweather", "http://xml.weather.yahoo.com/ns/rss/1.0");

        DocumentFactory documentFactory = new DocumentFactory();
        documentFactory.setXPathNamespaceURIs(namespace);

        SAXReader saxReader = new SAXReader();
        saxReader.setDocumentFactory(documentFactory);

        return saxReader;
    }
}
