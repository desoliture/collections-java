import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Kozka Ivan
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size = 0;
    private Node<K, V>[] data;

    @SuppressWarnings("unchecked")
    public HashMap() {
        data = (Node<K, V>[]) Array.newInstance(
                Node.class,
                INIT_CAPACITY);
    }

    private int getPos(K key, int arrayLength) {
        return Math.abs(key.hashCode() % arrayLength);
    }

    private void increase() {
        HashMap.Node<K, V>[] increased =
                (HashMap.Node<K, V>[]) new Object[INIT_CAPACITY];

        for (HashMap.Node<K, V> n : data) {
            while (n != null) {
                put(n.getKey(), n.getValue(), increased);
                n = n.next;
            }
        }

        data = increased;
    }

    private void put(K key, V value, Node<K, V>[] increased) {
        int pos = getPos(key, increased.length);
        Node<K, V> current = increased[pos];

        if (current == null) {
            increased[pos] = new Node<>(key, value, null);
        }
        else {
            while (current != null | current.next != null) {
                if (current.getKey().equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }

            current.next = new Node<>(key, value, null);
        }
    }

    @Override
    public void put(K key, V value) {
        if (size > (data.length * LOAD_FACTOR)) increase();
        put(key, value, data);
        size++;
    }

    private Node<K, V> getNodeBy(K key) {
        int pos = getPos(key, data.length);

        Node<K, V> current = data[pos];

        while(current != null) {
            if (current.key.equals(key)) return current;
            current = current.next;
        }

        throw new NoSuchElementException("Ti pidor!");
    }

    @Override
    public V get(K key) {
        return getNodeBy(key).value;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();

        for (var e : this) {
            set.add(e.getKey());
        }

        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();

        for (Node<K, V> n : data) {
            Node<K, V> current = n;

            while (current != null) {
                list.add(n.value);
                current = current.next;
            }
        }

        return list;
    }

    @Override
    public void remove(K key) {
        int pos = getPos(key, data.length);

        Node<K, V> current = data[pos];

        if (current.next != null && current.key.equals(key)) {
            data[pos] = current.next;
        }
        else {
            while (current != null) {
                if (current.next != null
                        && current.next.key.equals(key)) {
                    current.next = current.next.next;
                    break;
                }
                else if (current.next == null)
                    throw new NoSuchElementException();

                current = current.next;
            }
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        data = (HashMap.Node<K, V>[]) new Object[INIT_CAPACITY];
    }

    public List<Entry<K, V>> entryList() {
        List<Entry<K, V>> list = new ArrayList<>();

        for (Node<K, V> datum : data) {
            Node<K, V> current = datum;

            while (current != null) {
                list.add(current);
                current = current.next;
            }
        }

        return list;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entryList().iterator();
    }

    static class Node<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private Node<K, V> next;

        private Node(){}

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
