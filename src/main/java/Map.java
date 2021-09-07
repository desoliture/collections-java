/**
 * @author Kozka Ivan
 */
public interface Map <K, V>{
    void put(K key, V value);
    V get(K key);
    Set<K> keySet();
    List<V> values();
    void remove(K key);
    int size();
    void clear();

    interface Entry <K, V> {
        K getKey();
        V getValue();
    }
}
