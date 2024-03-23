package cn.jufe.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties = new Properties();
    private static final String File_Name = "/resource/file.properties";
    static {
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream(File_Name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getFileName(String key){
        return properties.getProperty(key);
    }
}
