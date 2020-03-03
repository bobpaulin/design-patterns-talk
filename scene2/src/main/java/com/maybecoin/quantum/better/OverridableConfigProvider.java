package com.maybecoin.quantum.better;

import java.util.Map;

/**
 * Created by fguime on 9/27/2018.
 */
public class OverridableConfigProvider implements ConfigProvider {
    final Map<String,String> overridenValue;
    final ConfigProvider delegate;

    public OverridableConfigProvider(
            Map<String, String> overridenValue, ConfigProvider delegate) {
        this.overridenValue = overridenValue;
        this.delegate = delegate;
    }



    @Override
    public String getConfig(String value) {
        if (overridenValue.containsKey(value)) {
            return overridenValue.get(value);
        }
        return delegate.getConfig(value);
    }
}
