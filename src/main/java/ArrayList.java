import java.util.Iterator;

/**
 * @author Kozka Ivan
 */
public class ArrayList <T> implements List<T> {
    protected T[] data;

    private final int INIT_CAPACITY = 10;
    private final int CAPACITY_STEP = 2;
    private int capacity;
    protected int size;

    public Iterator<T> iterator(){
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return data[index++];
            }
        };
    }

    public ArrayList() {
        data = (T[]) new Object[INIT_CAPACITY];
        capacity = INIT_CAPACITY;
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException("Ti pidor!");
    }

    private void increase() {
        if (size == capacity) {
            capacity *= CAPACITY_STEP;

            T[] newData = (T[]) new Object[capacity];

            for(int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    @Override
    public void add(T element) {
        increase();
        data[size++] = element;
    }

    @Override
    public T get(int idx) {
        checkIndex(idx);
        return data[idx];
    }

    @Override
    public void remove(int idx) {
        checkIndex(idx);

        if (size - 1 - idx >= 0) {
            size--;
            for (int i = idx; i < size; i++) {
                data[i] = data[i + 1];
            }
        }
    }

    @Override
    public void clear() {
        data = (T[]) new Object[INIT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != 0;
    }

    @Override
    public T[] toArray() {
        T[] copy = (T[]) new Object[size];

        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }

        return copy;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) return i;
        }
        return -1;
    }

    @Override
    public void addAll(Collection<T> collection) {
        for (T e : collection) {
            add(e);
        }
    }


    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(element)) return i;
        }
        return -1;
    }

    public String toString() {
        if (data == null)
            return "null";

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');

        for (int i = 0; ; i++) {
            b.append(data[i]);

            if (i == iMax)
                return b.append(']').toString();

            b.append(", ");
        }
    }
}
