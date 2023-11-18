package com.seed.databaseseed;

import java.util.HashMap;
import java.util.Map;

public class SharkCodeManager {
    private static int code = 1;
    private static Map<String, Integer> sharkCode = new HashMap<>();

    public Integer getSharkCode(String name) {
        if (sharkCode.containsKey(name)) return sharkCode.get(name);
        sharkCode.put(name, code++);
        return sharkCode.get(name);
    }
}
