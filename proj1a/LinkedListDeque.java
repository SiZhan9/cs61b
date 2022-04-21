public class LinkedListDeque<T> {
    private class TNode{
        public T item;
        public TNode next;
        public TNode previous;

        public TNode(T item, TNode next, TNode previous){
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
    public LinkedListDeque(){
        size = 0;
        sentinel = new TNode(null,null,null);
        sentinel.next = sentinel.previous;
    }

    /** Create a real first item */
    public LinkedListDeque(T i){
        sentinel = new TNode(null,null,null);
        sentinel.next = new TNode(i ,sentinel, sentinel);
        size = 1;
    }

    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel.next, sentinel);
        size += 1;
    }

    public void addLast(T item){
        sentinel.previous = new TNode(item, sentinel, sentinel.previous);
        size += 1;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (isEmpty()){
            return;
        }
        TNode p = sentinel.next;
        while(p.next!= null){
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst(){
        if (sentinel.next == sentinel.previous){
            return null;
        }else {
            TNode first_Node = sentinel.next;
            sentinel.next = first_Node.next;
            size -= 1;
         //   first_Node.next.next = sentinel;
            return first_Node.item;
        }
    }

    public T removeLast(){
        if (sentinel.next == sentinel.previous){
            return null;
        }else {
            TNode last_Node = sentinel.previous;
            sentinel.previous = last_Node.previous;
            size -= 1;
            //   first_Node.next.next = sentinel;
            return last_Node.item;
        }
    }

    public T get(int index){
        if (sentinel.next == sentinel.previous){
            return null;
        }else {
            TNode p = sentinel.next;
            while(p.next!= sentinel && index!=0){
                index--;
                p = p.next;
            }
            return  p.item;
        }
    }

    public T getRecursive(int index) {
        if (index > size-1){
            return null;
        }
        /** Get from start the Node p, search for the rest. */
        return getRecursiveHelper(sentinel.next, index).item;
    }

    private TNode getRecursiveHelper(TNode p, int index){
        if (index == 0){
            return p;
        }else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

}
