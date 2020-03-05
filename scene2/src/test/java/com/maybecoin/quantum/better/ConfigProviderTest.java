package com.maybecoin.quantum.better;

import java.util.Collections;
import org.junit.Test;

/**
 * Created by fguime on 9/27/2018.
 */
public class ConfigProviderTest {

    @Test
    public void createConfigProvider() {
        ConfigProvider provider =
                new OverridableConfigProvider(
                        Collections.emptyMap(),
                        new CachedConfigProvider(
                                new MultiConfigProvider(
                                        new JsonConfigProvider("json"),
                                        new PropertyConfigProvider("property")
                                )
                        )
                );
    }

}