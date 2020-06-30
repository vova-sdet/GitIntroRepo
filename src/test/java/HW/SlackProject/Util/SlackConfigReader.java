package HW.SlackProject.Util;

import java.io.FileInputStream;
import java.util.Properties;

public class SlackConfigReader {

    private static Properties properties;

    static {
        String path = "src/test/java/HW.SlackProject/Util/SlackConfigurations.properties";

        try {
            // this line will open your file
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            // We need to load opened file to the properties
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key) {
        // getProperty method takes one String as a key
        // and it will return value from .properties file
        return properties.getProperty(key);
    }
}
