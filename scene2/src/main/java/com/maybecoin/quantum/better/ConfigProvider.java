package com.maybecoin.quantum.better;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by fguime on 9/27/2018.
 */
public interface ConfigProvider {
    String getConfig(String value);
}
