import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Kozka Ivan
 */
public class TreeMap <K, V> implements Map<K, V> {
    private int size;
    public Node<K, V> root;

    private Comparator<K> comp;

    public TreeMap() {
        comp = null;
    }

    public TreeMap(Comparator<K> comp) {
        this.comp = comp;
    }

    private void put(K key, V value,
                     Node<K, V> current,
                     Comparator<K> comparator) {

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

    @SuppressWarnings("unchecked")
    private Comparator<K> getComparator(K key) {
        Comparator<K> comparator;

        if (comp != null)
            comparator = comp;
        else if (key instanceof Comparable)
            comparator = (e1, e2) ->
                    ((Comparable<K>) e1).compareTo(e2);
        else throw new RuntimeException("No comparator!");

        return comparator;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return;
        }

        put(key, value, root, getComparator(key));
    }

    private Node<K, V> getNodeBy(K key,
                                 Node<K, V> current,
                                 Comparator<K> comp) {

        if (current == null)
            throw new NoSuchElementException("Not found!");

        int res = comp.compare(current.key, key);

        if (res == 0) return current;

        return getNodeBy(key,
                res > 0 ? current.left : current.right,
                comp);
    }

    @Override
    public V get(K key) {
        emptyCheck();

        return getNodeBy(key, root,
                getComparator(key)).value;
    }



    private List<Node<K, V>> getChildren(Node<K, V> current,
                                         List<Node<K, V>> list) {

        list.add(current);
        if (current.left != null)
            getChildren(current.left, list);
        if (current.right != null)
            getChildren(current.right, list);

        return list;
    }

    @Override
    public void remove(K key) {
        emptyCheck();

        var toDelete =
                getNodeBy(key, root, getComparator(key));

        var children =
                getChildren(toDelete,
                        new ArrayList<>());

        for (var e : getChildren(root, new ArrayList<>())) {
            if (e.left == toDelete) e.left = null;
            else if (e.right == toDelete) e.right = null;
        }

        children.remove(children.indexOf(toDelete));

        addAll(children);
    }

    private void addAll(List<Node<K, V>> children) {
        for (var e : children) {
            put(e.key, e.value);
        }
    }

    @Override
    public Set<K> keySet() {
        //TODO: change to TreeSet when its ready

        emptyCheck();

        Set<K> res = new HashSet<>();
        getChildren(root, new ArrayList<>())
                .forEach(e -> res.add(e.key));

        return res;
    }

    private void emptyCheck() {
        if (root == null)
            throw new RuntimeException("Empty!");
    }

    @Override
    public List<V> values() {
        emptyCheck();

        List<V> res = new ArrayList<>();
        getChildren(root, new ArrayList<>())
                .forEach(e -> res.add(e.value));

        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public List<Entry<K, V>> entryList() {
        emptyCheck();

        List<Entry<K, V>> entries = new ArrayList<>();
        entries.addAll(getChildren(root, new ArrayList<>()));

        return entries;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entryList().iterator();
    }

    public static class Node <K, V> implements Map.Entry<K, V> {
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
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }
}
