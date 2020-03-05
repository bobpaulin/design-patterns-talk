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
public class ConfigHolderReloadMultipleOverrides {
    private final boolean reloadAlways;
    private final boolean isJson;
    private final String file;
    private final String file2;
    private final String file3;
    private final Map<String, String> overridenSettings;
    public Map<String,String> configInfo = new HashMap<>();

    public ConfigHolderReloadMultipleOverrides (String file, boolean isJson, boolean reloadAlways, String file2, String file3, Map<String,String> overriddenSettings) throws IOException {
        this.isJson = isJson;
        this.file = file;
        this.file2 = file2;
        this.file3 = file3;
        this.overridenSettings = overriddenSettings;

        this.reloadAlways = reloadAlways;
        reload();
    }

    private void reload() throws IOException {

        if (isJson) {
            ObjectMapper mapper = new ObjectMapper();
            configInfo.putAll(mapper.readValue(new File(file), new TypeReference<Map<String,String>>(){}));
            configInfo.putAll(mapper.readValue(new File(file2), new TypeReference<Map<String,String>>(){}));
            configInfo.putAll(mapper.readValue(new File(file3), new TypeReference<Map<String,String>>(){}));

        } else {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            properties.entrySet().forEach(e->configInfo.put(e.getKey().toString(), e.getValue().toString()));
        }
    }

    public String getConfig(String config) throws IOException {
        if (reloadAlways) reload();
        if (overridenSettings.containsKey(config)) {
            return overridenSettings.get(config);
        }
        return configInfo.get(config);
    }


}
