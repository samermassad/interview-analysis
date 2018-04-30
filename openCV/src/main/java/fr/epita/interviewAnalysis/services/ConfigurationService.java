package fr.epita.interviewAnalysis.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationService {

	private Properties properties;
	private static ConfigurationService instance;

	private final static String DEFAULTCONFPATH = "src/main/resources/conf.properties";

	private ConfigurationService() {
		try {
			properties = new Properties();
			File confFile = new File(DEFAULTCONFPATH);
			properties.load(new FileInputStream(confFile));
		} catch (final IOException e) {
			//LOGGER.error("couldn't open the configuration file", e);
			e.printStackTrace();
		}
	}
	
	public static ConfigurationService getInstance() {
		if (instance == null) {
			instance = new ConfigurationService();
		}
		return instance;
	}
	
	public String getConfigurationValue(String propertyKey) {
		return properties.getProperty(propertyKey);
	}
	
}
