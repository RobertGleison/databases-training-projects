package com.seed.databaseseed.parseCsvToRelational;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This class is responsible to generate entrepeneur id.
//Some pithces have 2 entrepeneurs or more and because of that, the id is not the number of the pitch.
@Component
public class EntrepeneurCodeManager {
    private static int code = 1;
    private static Map<String, Integer> entrepeneurCode = new HashMap<>();

    public static List<Integer> getEntrepeneurCode(List<String> names) {
        List<Integer> tempCode = new ArrayList<>();
        for (String name : names) {
            tempCode.add(getOrGenerateCode(name));
        }
        return tempCode;
    }

    private static int getOrGenerateCode(String name) {
        if (entrepeneurCode.containsKey(name)) {
            return entrepeneurCode.get(name);
        }
        entrepeneurCode.put(name, code++);
        return entrepeneurCode.get(name);
    }
}