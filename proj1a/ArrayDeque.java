public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    // Add the last item of to the array
    public void addFirst(T x) {
        size = size + 1;
        T[] newitems = (T[]) new Object[size];
        System.arraycopy(items, 0, newitems, 1, size - 1);
        newitems[0] = x;
        items = newitems;
    }

    private void resize(int newsize) {
        T[] newitems = (T[]) new Object[newsize];
        System.arraycopy(items, 0, newitems, 0, size);
        items = newitems;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size] = x;
        size = size + 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.println(items[i]);
            i++;
        }
    }

    public T removeFirst() {
        T a = items[0];
        size = size - 1;
        T[] newitems = (T[]) new Object[size];
        System.arraycopy(items, 1, newitems, 0, size);
        items = newitems;
        return a;
    }

    public T removeLast() {
        T a = items[size - 1];
        items[size - 1] = null;
        size = size - 1;
        return a;
    }

    // Get the item in the i-th position
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return items[index];
    }
}
