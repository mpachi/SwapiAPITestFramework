package Utils;

import io.restassured.path.json.JsonPath;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    public static String getGlobalValue(String key) throws IOException {
        String filepath = System.getProperty("user.dir") + "/src/test/java/resources/";
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(filepath + "env.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

}
