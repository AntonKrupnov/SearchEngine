package com.github.yell13.search.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Anton 14.05.2018
 */
@Service
public class SearchEngine {

    private static final String SPLIT_REGEX = "\\W";

    private ConcurrentMap<String, Set<String>> map = new ConcurrentHashMap<>();

    public void addToIndex(String key, String document) {
        for (String word: document.split(SPLIT_REGEX)) {
            map.putIfAbsent(word, new HashSet<>());
            map.get(word).add(key);
        }
    }

    public Set<String> findKeys(String tokens) {
        Set<String> result = new HashSet<>();
        for (String token: tokens.split(SPLIT_REGEX)) {
            if (token != null && !token.isEmpty()) {
                result.addAll(findKeysOfOneToken(token));
            }
        }
        return result;
    }

    private Set<String> findKeysOfOneToken(String token) {
        return map.getOrDefault(token, Collections.emptySet());
    }
}
