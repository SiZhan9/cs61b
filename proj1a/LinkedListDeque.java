public class LinkedListDeque <T> {
    private class TNode {
        public T item;
        public TNode next;
        public TNode previous;

        public TNode(T item, TNode next, TNode previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;

        }
    }
    private TNode sentinel;
    private int size;
    /**
     * add and remove should take constant time
     * get should use iteration, not recursion
     * getRecursion should be involved recursion method
     */


    /** add First item, Constructor */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    /** Create a real first item */
    public LinkedListDeque(T i) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(i ,sentinel, sentinel);
        sentinel.previous = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        TNode first_ptr = sentinel.next;
        sentinel.next = new TNode(item, first_ptr, sentinel);
        first_ptr.previous = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        TNode last_ptr = sentinel.previous;
        sentinel.previous = new TNode(item, sentinel, last_ptr);
        last_ptr.next = sentinel.previous;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel;
        while(p.next != sentinel) {
            p =p.next;
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        TNode p = sentinel.next;
        if(p != sentinel) {
            sentinel.next = p.next;
            p.next.previous = p.previous;
            size -= 1;
            }
        return p.item;
    }

    public T removeLast() {
        TNode p = sentinel.previous;
        if (p != sentinel){
            sentinel.previous = p.previous;
            p.previous.next = sentinel;
            size -= 1;}
        //   first_Node.next.next = sentinel;
        return p.item;
    }

    public T get(int index) {
        TNode p = sentinel;
        while(p.next != sentinel && index >= 0){
            p = p.next;
            index --;
        }
        return  p.item;
    }

    public T getRecursive(int index) {
        if (index > size-1){
            return null;
        }
        /** Get from start the Node p, search for the rest. */
        return getRecursiveHelper(sentinel.next, index).item;
    }

    private TNode getRecursiveHelper(TNode p, int index) {
        if (index == 0) {
            return p;
        }else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

}
