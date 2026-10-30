package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WildcardTest {

    @Test
    void listsAcceptCollectionOfSubtype() {
        List<Integer> ints = List.of(1, 2, 3);

        MyList<Number> arrayList = new MyArrayList<>();
        arrayList.addAll(ints);
        MyList<Number> linkedList = new MyLinkedList<>();
        linkedList.addAll(ints);

        assertArrayEquals(new Object[]{1, 2, 3}, arrayList.toArray());
        assertArrayEquals(new Object[]{1, 2, 3}, linkedList.toArray());
    }

    @Test
    void setAcceptsCollectionOfSubtype() {
        MyLinkedHashSet<Number> set = new MyLinkedHashSet<>();
        assertTrue(set.addAll(List.of(1, 2, 2, 3)));
        assertFalse(set.addAll(List.of(1, 2)));
        assertEquals(3, set.size());
    }

    @Test
    void cacheAcceptsMapOfSubtypes() {
        MyCache<String, Number> cache = new MyCache<>(5, 0);
        cache.putAll(Map.of("a", 1, "b", 2));
        assertEquals(1, cache.get("a"));
        assertEquals(2, cache.get("b"));
    }
}