package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyList<String> words = new MyArrayList<>();
        words.add("a");
        words.add("b");
        words.add(1, "x");
        words.addAll(List.of("p", "q"));
        System.out.println(Arrays.toString(words.toArray()));

        // Wildcard: у список Number можна додати колекцію Integer
        MyList<Number> numbers = new MyLinkedList<>();
        numbers.addAll(List.of(1, 2, 3));
        System.out.println(Arrays.toString(numbers.toArray()));
    }
}