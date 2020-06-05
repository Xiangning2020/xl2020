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
    public LinkedListDeque(LinkedListDeque other) {
        if (other.size() == 0) {
            return;
        }
        Node Nodeother = other.sentinel.next;
        size = other.size();
        Node newother = new Node(Nodeother.item, sentinel, null);
        Node head = newother;

        while (other != null) {
            Nodeother = Nodeother.next;
            newother.next = new Node(Nodeother.item, newother, null);
            newother = newother.next;
        }
        sentinel.next = head;
        sentinel.prev = newother;
        newother.next = sentinel;
    }
    public void addFirst(T item) {
        Node oldfirst = sentinel.next; //Refer to the old first by sentinel.
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
        if (size == 0) {
            return null;
        }
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
        if (size == 0) {
            return null;
        }
        return oldlast.item;
    }
    public T get(int index) {
        if ( index > size - 1 || index < 0) {
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
        first = first.next;
        return this.getRecursive(index - 1);
    }
}
