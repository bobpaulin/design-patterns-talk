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
public class ConfigHolderReloader {
    private final boolean reloadAlways;
    private final boolean isJson;
    private final String file;
    public Map<String,String> configInfo = new HashMap<>();

    public ConfigHolderReloader(String file, boolean isJson, boolean reloadAlways) throws IOException {
        this.isJson = isJson;
        this.file = file;
        this.reloadAlways = reloadAlways;
        reload();
    }

    private void reload() throws IOException {

        if (isJson) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(new File(file), new TypeReference<Map<String,String>>(){});
        } else {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            properties.entrySet().forEach(e->configInfo.put(e.getKey().toString(), e.getValue().toString()));
        }
    }

    public String getConfig(String config) throws IOException {
        if (reloadAlways) reload();
        return configInfo.get(config);
    }

}
