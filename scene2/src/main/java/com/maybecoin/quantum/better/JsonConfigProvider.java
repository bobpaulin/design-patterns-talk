package com.maybecoin.quantum.better;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fguime on 9/27/2018.
 */
public class JsonConfigProvider implements ConfigProvider {
    final String jsonFile;

    public JsonConfigProvider(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    @Override
    public String getConfig(String value) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> configs = null;
        try {
            configs = mapper.readValue(new File(jsonFile), new TypeReference<Map<String,String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configs.get(value);
    }
}
