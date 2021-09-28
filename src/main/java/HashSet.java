import java.util.Iterator;

/**
 * @author Kozka Ivan
 */
public class HashSet<T> implements Set<T>{

    private HashMap<T, Object> map;

    public HashSet() {
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.size() == 0;
    }

    @Override
    public void add(T element) {
        map.put(element, null);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(T element) {
        for (T e : this) {
            if (e.equals(element)) return true;
        }

        return false;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[map.size()];

        int counter = 0;
        for (var e : map.keySet()) {
            array[counter] = e;
            counter++;
        }

        return array;
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        for (var c : collection) {
            add(c);
        }
    }

    @Override
    public void remove(T e) {
        map.remove(e);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Iterator<Map.Entry<T, Object>> iter
                    = map.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                return iter.next().getKey();
            }
        };
    }
}
