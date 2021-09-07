/**
 * @author Kozka Ivan
 */
public interface Queue<T> extends Collection<T> {
    void offer(T element);
    T poll();
    T peek();
}
