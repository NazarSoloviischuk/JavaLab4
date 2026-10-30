package org.example;

class MyLinkedListTest extends MyListTest {

    @Override
    protected MyList<Object> createList() {
        return new MyLinkedList<>();
    }
}