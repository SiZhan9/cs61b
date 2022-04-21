public class ArrayDeque<T> {
    //** Circular Array Method */
    private int size;
    private int nextFirst;
    private  int nextLast;
    private T[] items;
    //** Create an empty array deque. */

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

    }

    /***
     * How to judge that array is full:
     * LastNext LastFirst, Array.length
     * 0,1,2,3,4,5,6,7 |length|
     *     F L          empty
     *     L F          Almost Full, add one more, violate rule
     *  Be careful of minus operations
     *  Follow-Up question: When to resize?
     *  F > L
    */
    public void addFirst(T item){
        if (size >= items.length - 1) {
            resize();
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length)%items.length;
        size += 1;
    }

    public void addLast(T item){
        if (size >= items.length - 1) {
            resize();
        }
        items[nextLast] = item;
        nextLast = (nextLast+1)%items.length;
        size += 1;
    }

    public boolean isEmpty(){
        return size<=0;
    }

    //** Start size should be 8. */
    // For arrays of length 16 or more, your usage factor should always be higher than 25%.
    // /
    public int size(){
        return size;
    }

    public void printDeque(){
        int ptr = 0;
        while (ptr < size){
            System.out.print(items[(nextFirst+1+ptr)%items.length]+ " " );
            ptr += 1;
        }
    }
    /** 1. Create a new Array with new size,
     *  2. Copy the old Array back to the new array, Use the System.arrayCopy()
     *  3. Discard the old Array.
     * */
    private void resize(){
        int new_length = 4 * size; // The four can tune later; This can ensuare the usage larger than 0.25
        T[] items_new = (T[]) new Object[new_length];
        System.arraycopy(items,0,items_new,0, size);
        items = items_new;
    }

    public T removeFirst(){
        if (size<=0){
            return null;
        }
        int new_nextFirst = (nextFirst + 1 + items.length)%items.length;
        T first = items[new_nextFirst];
        items[new_nextFirst] = null;
        nextFirst = new_nextFirst;
        size -= 1;
        return first;
    }

    public T removeLast(){
        if (size<=0){
            return null;
        }
        int new_nextLast = (nextLast - 1 + items.length)%items.length;
        T last = items[new_nextLast];
        items[new_nextLast] = null;
        nextLast = new_nextLast;
        size -= 1;
        return last;
    }

    public T get(int index){
        if (index>size) {
            return null;
        }else{
            int array_index = (nextLast - 1 + index + items.length)%items.length;
            return items[array_index];
        }
    }

}
