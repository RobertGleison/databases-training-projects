package com.seed.databaseseed.parseCsvToRelational;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntrepeneurCodeManager {
    private static int code = 1;
    private static Map<String, Integer> entrepeneurCode = new HashMap<>();

    public static List<Integer> getEntrepeneurCode(List<String> names) {
        List<Integer> tempCode = new ArrayList<>();

        //@TODO remove code repetition
        if (names.size() == 1) {
            if (entrepeneurCode.containsKey(names.get(0))) {
                tempCode.add(entrepeneurCode.get(names.get(0)));
                return tempCode;
            }
            entrepeneurCode.put(names.get(0), code++);
            tempCode.add(entrepeneurCode.get(names.get(0)));
            return tempCode;
        } else {
            for (String name : names) {
                if (entrepeneurCode.containsKey(name)) tempCode.add(entrepeneurCode.get(name));
                entrepeneurCode.put(name, code++);
                tempCode.add(entrepeneurCode.get(name));
            }
            return tempCode;
        }
    }
}

