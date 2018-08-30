package fr.epita.interviewAnalysis.test;

import org.junit.BeforeClass;

import fr.epita.interviewAnalysis.services.ConfigurationService;

public class BackgroundAnalyserTests {

	@BeforeClass
	public static void loadLibrary() {
		ConfigurationService conf = ConfigurationService.getInstance();
		String path = conf.getConfigurationValue("library.path");
		System.load(path);
	}
	
	
}

	
	
