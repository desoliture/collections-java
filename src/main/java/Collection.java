import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Kozka Ivan
 */
public interface Collection<T> extends Iterable<T> {
    int size();
    boolean isEmpty();
    void add(T element);
    void clear();
    boolean contains(T element);
    T[] toArray();
    void addAll(Collection<T> collection);
}
