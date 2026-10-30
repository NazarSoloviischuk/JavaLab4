package org.example;

import java.util.Collection;
import java.util.RandomAccess;

public class MyArrayList<E> implements MyList<E>, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int needed) {
        if (needed > data.length) {
            int newCapacity = Math.max(needed, data.length * 3 / 2 + 1);
            Object[] bigger = new Object[newCapacity];
            System.arraycopy(data, 0, bigger, 0, size);
            data = bigger;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    // Масив зберігає Object, тому при читанні доводиться повертатися до E
    @SuppressWarnings("unchecked")
    private E elementAt(int index) {
        return (E) data[index];
    }

    @Override
    public void add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Collection<? extends E> c) {
        checkIndexForAdd(index);
        Object[] items = c.toArray();
        if (items.length == 0) {
            return;
        }
        ensureCapacity(size + items.length);
        System.arraycopy(data, index, data, index + items.length, size - index);
        System.arraycopy(items, 0, data, index, items.length);
        size += items.length;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E old = elementAt(index);
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(data, index + 1, data, index, moved);
        }
        data[--size] = null;
        return old;
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        data[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? data[i] == null : o.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        System.arraycopy(data, 0, copy, 0, size);
        return copy;
    }
}