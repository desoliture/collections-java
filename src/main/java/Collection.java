import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Kozka Ivan
 */
public interface Collection<T> extends Iterable<T> {
    int size();
    boolean isEmpty();
    void add(T element);
    T get(int idx);
    void remove(int idx);
    void clear();
    boolean contains(T element);
    T[] toArray();
    int indexOf(T element);
    void addAll(Collection<T> collection);
}
