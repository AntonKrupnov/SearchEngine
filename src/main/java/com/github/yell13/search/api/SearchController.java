package com.github.yell13.search.api;

import com.github.yell13.search.service.SearchEngine;
import com.github.yell13.search.service.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Anton 14.05.2018
 */
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final Storage storage;
    private final SearchEngine searchEngine;

    @PutMapping("/documents/{key}")
    @ResponseBody
    public boolean storeDocument(@PathVariable String key, @RequestBody String document) {
        searchEngine.addToIndex(key, document);
        return storage.put(key, document);
    }

    @GetMapping("/documents/{key}")
    @ResponseBody
    public String getDocument(@PathVariable("key") String key) {
        return storage.get(key);
    }

    @GetMapping("/keys")
    public Set<String> findKeysWithTokens(@RequestParam String tokens) {
        return searchEngine.findKeys(tokens);
    }
}
