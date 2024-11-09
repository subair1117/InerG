package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ReadProp {
	Properties properties;
	FileInputStream fStream;
	FileOutputStream foutStream;

	public Properties getPropData() {
		String propFilePath = System.getProperty("user.dir");
		try{fStream = new FileInputStream(propFilePath + "\\resources\\dat.properties");
		properties = new Properties();
		properties.load(fStream);}catch(Exception e) {}
		return properties;
	}
}
