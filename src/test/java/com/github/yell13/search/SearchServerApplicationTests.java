package com.github.yell13.search;

import com.github.yell13.search.api.SearchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServerApplicationTests {

    @Autowired
    private SearchController searchController;

    @Test
	public void shouldStoreDocument() {
        searchController.storeDocument("key", "a document");
        String document = searchController.getDocument("key");

        assertEquals("a document", document);
    }

    @Test
    public void shouldFindAllKeysForToken() {
        searchController.storeDocument("key1", "a document");
        searchController.storeDocument("key2", "another document");
        searchController.storeDocument("key3", "something else");

        Set<String> keys = searchController.findKeysWithTokens("document");

        assertNotNull(keys);
        assertEquals(2, keys.size());
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
    }

    @Test
    public void shouldFindForAllTokens() {
        searchController.storeDocument("key1", "first document");
        searchController.storeDocument("key2", "second document");
        searchController.storeDocument("key3", "another document");

        Set<String> keys = searchController.findKeysWithTokens("first second");

        assertNotNull(keys);
        assertEquals(2, keys.size());
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
    }
}
