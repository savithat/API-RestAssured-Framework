package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream fileIp;

	public Properties init() {
		prop = new Properties();

		// mvn command line argument:
		// mvn clean install -Denvqa="qa"

		String env = System.getProperty("env");

		try {
			if (env == null) {
				System.out.println("No environment given so running qa env.. ");
				fileIp = new FileInputStream("./src/test/resouces/config/qa.config.properties");
			} else {
				System.out.println("running on environment: " + env);

				switch (env.toLowerCase()) {
				case "dev":
					fileIp = new FileInputStream("./src/test/resouces/config/dev.config.properties");
					break;

				case "qa":
					fileIp = new FileInputStream("./src/test/resouces/config/qa.config.properties");
					break;
				case "stage":
					fileIp = new FileInputStream("./src/test/resouces/config/stage.config.properties");
					break;
				default:
					System.out.println("Please enter the proper environment name");
					throw new APIFrameworkException("No env is given");
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fileIp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
