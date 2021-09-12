/**
 * @author Kozka Ivan
 */
public interface List<T> extends Collection<T>{
    T get(int idx);
    void remove(int idx);
    int lastIndexOf(T element);
    int indexOf(T element);
}
