import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Kozka Ivan
 */
public class TreeMap <K, V> implements Map<K, V> {
    private int size;
    private Node<K, V> root;

    Comparator<K> comp;

    public TreeMap() {
        comp = null;
    }

    public TreeMap(Comparator<K> comp) {
        this.comp = comp;
    }

    private void put(K key, V value, Node<K, V> current, Comparator<K> comparator) {
        int res = comparator.compare(current.key, key);

        if (res > 0) {
            if (current.left == null) {
                current.left = new Node<>(key, value);
                size++;
            }
            else put(key, value, current.left, comparator);
        }
        else if (res < 0) {
            if (current.right == null) {
                current.right = new Node<>(key, value);
                size++;
            }
            else put(key, value, current.right, comparator);
        }
        else {
            current.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return;
        }

        Comparator<K> comparator;

        if (comp != null)
            comparator = comp;
        else if (key instanceof Comparable)
            comparator = (e1, e2) ->
                    ((Comparable<K>) e1).compareTo(e2);
        else throw new RuntimeException("No comparator!");

        put(key, value, root, comparator);
    }

    @Override
    public V get(K key) {
        if (root == null)
            throw new NullPointerException("Empty!");

        //BE BACK SOON

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
        root = null;
    }

    private List<Entry<K, V>> entryList() {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entryList().iterator();
    }

    private static class Node <K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        Node<K, V> left;
        Node<K, V> right;

        public Node() {
        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }
}
