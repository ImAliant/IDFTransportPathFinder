package fr.u_paris.gla.crazytrip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.u_paris.gla.crazytrip.App;

/**
 * Utility class to read application properties
 */
public class ApplicationUtils {
	/** Prevent instantiation */
    private ApplicationUtils() {}

	/**
	 * Read application properties from application.properties file
	 * @return Properties object containing application properties
	 */
    public static Properties readApplicationProperties() {
		Properties props = new Properties();

		try (InputStream is = App.class.getResourceAsStream("application.properties")) {
			props.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read application informations", e);
		}
		return props;
	}
}
