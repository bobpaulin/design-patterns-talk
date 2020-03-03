package com.maybecoin.quantum.better;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by fguime on 9/27/2018.
 */
public class PropertyConfigProvider implements ConfigProvider {
    final String fileName;

    public PropertyConfigProvider(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getConfig(String value) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(value);
    }
}
