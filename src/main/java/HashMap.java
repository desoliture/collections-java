/**
 * @author Kozka Ivan
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size = 0;
    private HashMap.Node<K, V>[] data =
            (HashMap.Node<K, V>[]) new Object[INIT_CAPACITY];


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

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public List<V> values() {
        return null;
    }

    @Override
    public void remove(K key) {

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

    static class Node<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private Node<K, V> next;

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
