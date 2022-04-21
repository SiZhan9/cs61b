public class ArrayDeque<T> {
    //** Circular Array Method */
    private int size;
    private int nextFirst;
    private  int nextLast;
    private T[] items;
    //** Create an empty array deque. */

    public ArrayDeque() {
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
    public void addFirst(T item) {
        if (size >= items.length - 1) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size >= items.length - 1) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    //** Start size should be 8. */
    // For arrays of length 16 or more, your usage factor should always be higher than 25%.
    // /
    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = 0;
        while (ptr < size) {
            System.out.print(items[(nextFirst + 1 + ptr) % items.length] + " " );
            ptr += 1;
        }
        System.out.print("\n");
    }
    /** 1. Create a new Array with new size,
     *  2. Copy the old Array back to the new array, Use the System.arrayCopy()
     *  3. Discard the old Array.
     * */
    private void resize(int capacity) {
        int head = (nextFirst + 1 + items.length) % items.length;
        int tail = (nextLast - 1 + items.length) % items.length;
        int newHead = 0;
        int newTail = tail - head;
        int new_length = capacity;
        T[] items_new = (T[]) new Object[new_length];
        if (tail >= head) {
            System.arraycopy(items, head,items_new, 0, tail-head+1);
        }

        else{
            System.arraycopy(items, head, items_new, 0, items.length - head);
            System.arraycopy(items, 0, items_new, items.length - head, tail+1);

            //** Copy the old items to New items, with the head to be zero */
            //newHead = Head;
            newTail = tail + items.length - head;
        }
        nextFirst = (newHead - 1 + items_new.length) % items_new.length;
        nextLast = (newTail + 1 + items_new.length) % items_new.length;
        items = items_new;
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (size <= 0.3 * items.length){
            resize(size * 2);
        }
        int new_nextFirst = (nextFirst + 1 + items.length)%items.length;
        T first = items[new_nextFirst];
        items[new_nextFirst] = null;
        nextFirst = new_nextFirst;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (size<=0.3 * items.length){
            resize(size * 2);
        }
        int new_nextLast = (nextLast - 1 + items.length) % items.length;
        T last = items[new_nextLast];
        items[new_nextLast] = null;
        nextLast = new_nextLast;
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (index>size) {
            return null;
        } else {
            int array_index = (nextFirst + 1 + index + items.length) % items.length;
            return items[array_index];
        }
    }
}