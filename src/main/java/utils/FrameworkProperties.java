package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
	
	private InputStream istream;
	
	public String getProperty(String key) throws IOException {
		/*Gets property from the framework.properties file*/
		Properties properties = new Properties();
		
		String propName = Constants.PROP_FILE_NAME;
		
		istream = getClass().getClassLoader().getResourceAsStream(propName);
		
		if (istream != null) {
			properties.load(istream);
		} else {
			throw new FileNotFoundException("Properties File not Found!");
		}
		
		String propertyValue = properties.getProperty(key);
		
		
		
		return propertyValue;
		
	}
}
