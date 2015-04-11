package com.jhillix.yahoo.weather;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;


/**
 * Formats the weather data for the CLI.
 *
 * @author jhillix
 */
public class WeatherFormatter {

    private static Logger LOG = Logger.getLogger(WeatherFormatter.class);

    public WeatherFormatter() {}

    /**
     * Take a Weather object and prep for presentation on the CLI.
     *
     * @param weather  Weather object
     * @return         formatted String
     */
    public String format(final Weather weather) {
        LOG.info("Formatting weather data.");
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("FormattedWeather.vm"));

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("weather", weather);
        StringWriter stringWriter = new StringWriter();

        try {
            Velocity.evaluate(velocityContext, stringWriter, "", reader);
        } catch (IOException ex) {
            LOG.error(ex.getStackTrace());
        }

        return stringWriter.toString();
    }
}
