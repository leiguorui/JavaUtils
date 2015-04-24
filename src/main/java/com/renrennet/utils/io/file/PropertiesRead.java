package com.renrennet.utils.io.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: Green lei
 * Date: 15-4-22
 * Time: 上午11:54
 */
public class PropertiesRead {
    public static String getValue(String key){
        Properties prop = new Properties();
        InputStream input = null;
        String value = null;

        try {

            input = PropertiesRead.class.getResourceAsStream("/paramter.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return value;
    }
}
