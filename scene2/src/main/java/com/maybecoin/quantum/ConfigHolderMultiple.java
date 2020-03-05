package com.maybecoin.quantum;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * Created by fguime on 9/27/2018.
 */
public class ConfigHolderMultiple {
    public Map<String,String> configInfo = new HashMap<>();

    public ConfigHolderMultiple(String file, boolean isJson) throws IOException {

        if (isJson) {
            ObjectMapper mapper = new ObjectMapper();
            configInfo.putAll(mapper.readValue(new File(file), new TypeReference<Map<String,String>>(){}));
        } else {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            properties.entrySet().forEach(e->configInfo.put(e.getKey().toString(), e.getValue().toString()));
        }
    }

    public String getConfig(String config) {
        return configInfo.get(config);
    }

}
