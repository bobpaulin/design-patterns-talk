package com.maybecoin.quantum.better;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fguime on 9/27/2018.
 */
public class CachedConfigProvider implements ConfigProvider {
    final ConfigProvider delegate;
    Map<String,String> cachedValues = new HashMap<>();

    public CachedConfigProvider(ConfigProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getConfig(String value) {
        return cachedValues.computeIfAbsent(value, delegate::getConfig);
    }
}
