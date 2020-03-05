package com.maybecoin.quantum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by fguime on 9/27/2018.
 */
public class ConfigHolder {
    public Map<String,String> configInfo = new HashMap<>();

    public ConfigHolder(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        properties.forEach((key, value) -> configInfo.put(key.toString(), value.toString()));
    }

    public String getConfig(String config) {
        return configInfo.get(config);
    }

}
