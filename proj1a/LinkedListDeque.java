public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;
    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        Node oldfirst = sentinel.next;
        Node newfirst = new Node(item, sentinel, oldfirst);
        sentinel.next = newfirst;
        oldfirst.prev = newfirst;
        size = size + 1;
    }
    public void addLast(T item) {
        Node oldlast = sentinel.prev;
        Node newlast = new Node(item, oldlast, sentinel);
        oldlast.next = newlast;
        sentinel.prev = newlast;
        size = size + 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.println(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node oldfirst = sentinel.next;
        Node newfirst = oldfirst.next;
        sentinel.next = newfirst;
        newfirst.prev = sentinel;
        size = size - 1;
        return oldfirst.item;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node oldlast = sentinel.prev;
        Node newlast = oldlast.prev;
        sentinel.prev = newlast;
        newlast.next = sentinel;
        size = size - 1;
        return oldlast.item;
    }
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        int i = 0;
        Node current = sentinel.next;
        while (i != index) {
            current = current.next;
            i++;
        }
        return current.item;
    }
    public T getRecursive(int index) {
        if (size - 1 < index  || index < 0) {
            return null;
        }
        Node first = sentinel.next;
        if (index == 0) {
            return first.item;
        }
        sentinel = sentinel.next;
        size = size - 1;
        return getRecursive(index - 1);
    }
}
