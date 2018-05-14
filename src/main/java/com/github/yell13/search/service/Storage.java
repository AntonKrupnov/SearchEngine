package com.github.yell13.search.service;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Anton 14.05.2018
 */
@Repository
public class Storage {

    private Map<String, String> map = new HashMap<>();

    public boolean put(String key, String document) {
        map.put(key, document);
        return true;
    }

    public String get(String key) {
        return map.get(key);
    }
}
