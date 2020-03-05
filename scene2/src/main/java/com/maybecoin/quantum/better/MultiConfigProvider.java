package com.maybecoin.quantum.better;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by fguime on 9/27/2018.
 */
public class MultiConfigProvider implements ConfigProvider {
    final Collection<ConfigProvider> providers;

    public MultiConfigProvider(ConfigProvider ...providers) {
        this.providers = Arrays.asList(providers);
    }

    @Override
    public String getConfig(String value) {
        return providers.stream()
                .map(e->getConfig(value))
                .findFirst()
                .orElse(null);

    }
}
