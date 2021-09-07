/**
 * @author Kozka Ivan
 */
public class Stack <T> extends ArrayList<T> {
    public void push(T element) {
        super.add(element);
    }

    public T peek() {
        return super.get(size - 1);
    }

    public T pop() {
        T lastElem = peek();
        super.remove(size - 1);
        return lastElem;
    }
}
