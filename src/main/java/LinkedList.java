import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Kozka Ivan
 */
public class LinkedList <T> implements List<T>{
    //TODO: 1. check iterator 2. set(idx, elem)

    private Node<T> head;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException("Ti pidor!");
    }

    @Override
    public void add(T element) {
        if (head == null) {
            head = new Node<>(element, null);
        }
        else {
            Node<T> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node<>(element, null);
        }
        size++;
    }

    private Node<T> getNode(int idx) {
        checkIndex(idx);

        Node<T> current = head;
        int currentI = 0;

        while (current.next != null) {
            if (currentI == idx) return current;
            currentI++;
            current = current.next;
        }

        throw new NoSuchElementException("Ti pidor!");
    }

    public void add(int idx, T element) {
        checkIndex(idx);

        if (idx != 0) {
            Node<T> previous = getNode(idx - 1);
            Node<T> next = previous.next;

            previous.next = new Node<>(element, next);
        }
        else {
            Node<T> next = head;

            head = new Node<>(element, next);
        }
        size++;
    }

    @Override
    public T get(int idx) {
        checkIndex(idx);

        Node<T> current = head;
        int currentI = 0;

        while (currentI < size) {
            if (currentI == idx) return current.data;
            current = current.next;
            currentI++;
        }

        throw new IndexOutOfBoundsException("Ti pidor!");
    }

    @Override
    public void remove(int idx) {
        checkIndex(idx);

        Node<T> previous = getNode(idx - 1);
        Node<T> next = previous.next;

        previous.next = next.next;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public boolean contains(T element) {
        for (T t : this) {
            if (t == element) return true;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];

        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }

        return array;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) return i;
        }

        return -1;
    }

    @Override
    public void addAll(Collection<T> collection) {
        for (T t : collection) {
            add(t);
        }
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (get(i).equals(element)) return i;
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> first;

            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public T next() {
                T e = first.data;
                first = first.next;
                return e;
            }
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        T[] array = toArray();

        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(", ");
        }

        return sb.substring(0, sb.length() - 2) + "]";
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
