import org.junit.Test;

public class LinkedListDeque<T>{
    // We made a node called sentinel, which plays both the first and the last part.
    private node sentinel;
    private int size;

    private class node{
        public T item;
        public node prev;
        public node next;
        public node(T i, node p, node n){
            item = i;
            prev = p;
            next = n;
        }
    }

    //Start a deque by setting the item to be the first node of the list.

    public LinkedListDeque(){
        sentinel = new node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    // have to understand whether it has destory the original one or not.
    public LinkedListDeque(LinkedListDeque other){
        if(other.size() == 0) return;
        node nodeother = other.sentinel.next;
        size = other.size();
        // Create the first node.
        node newother = new node(nodeother.item,sentinel,null);
        node head = newother;
         // we could use sentinel = newother as well since the address
        // the address is actually the thing we want.

        // if other is not equal to null, means that it will have its own item.
        while(other!=null){
            nodeother = nodeother.next;
            newother.next = new node(nodeother.item,newother,null);
            newother = newother.next;
        }
        sentinel.next = head;
        sentinel.prev = newother;
        newother.next = sentinel;
        // Copy the bits, so just copy the item and we give it a new address.
        //node newother = new node(other.item,other.prev,other.next);

    }
    public void addFirst(T item){
        node oldfirst = sentinel.next; //Refer to the old first by sentinel.
        node newfirst = new node(item,sentinel,oldfirst);
        sentinel.next = newfirst;
        oldfirst.prev = newfirst;
        size = size + 1;
    }
    public void addLast(T item){
        node oldlast = sentinel.prev;
        node newlast = new node(item,oldlast,sentinel);
        oldlast.next = newlast;
        sentinel.prev = newlast;
        size = size + 1;
    }
    public boolean isEmpty(){
        if(size == 0) {
            return true;
        } else {
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        node current = sentinel.next;
        while(current!= sentinel){
            System.out.println(current.item+" ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size == 0) {
            return null;
        }
        node oldfirst = sentinel.next;
        node newfirst = oldfirst.next;
        sentinel.next = newfirst;
        newfirst.prev = sentinel;
        size = size - 1;
        if(size == 0){
            return null;
        }
        return newfirst.item;

    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        node oldlast = sentinel.prev;
        node newlast = oldlast.prev;
        sentinel.prev = newlast;
        newlast.next = sentinel;
        size = size - 1;
        if(size == 0){
            return null;
        }
        return newlast.item;
    }
    public T get(int index){
        if(size < index || index < 0) return null;
        int i = 0;
        node current = sentinel.next;
        while(i!=index){
            current = current.next;
            i++;
        }
        return current.item;
    }
    public T getRecursive(int index){
        if(size - 1 < index  || index < 0) return null;
        node first = sentinel.next;
        if(index == 0) {

            return first.item;
        }
            first = first.next;
            return this.getRecursive(index-1);

    }

}
