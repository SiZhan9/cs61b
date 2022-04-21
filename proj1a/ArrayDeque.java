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
            System.out.print(items[ (nextFirst + 1 + ptr) % items.length ] + " ");
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
        T[] itemsNew = (T[]) new Object[capacity];
        if (tail >= head) {
            System.arraycopy(items, head,itemsNew, 0, tail - head + 1);
        } else {
            System.arraycopy(items, head, itemsNew, 0, items.length - head);
            System.arraycopy(items, 0, itemsNew, items.length - head, tail + 1);

            /*
            ** Copy the old items to New items, with the head to be zero * /
            newHead = Head;
            */
            newTail = tail + items.length - head;
        }
        nextFirst = (newHead - 1 + itemsNew.length) % itemsNew.length;
        nextLast = (newTail + 1 + itemsNew.length) % itemsNew.length;
        items = itemsNew;
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (size <= 0.3 * items.length) {
            resize(size * 2);
        }
        int newNextFirst = (nextFirst + 1 + items.length) % items.length;
        T first = items[newNextFirst];
        items[newNextFirst] = null;
        nextFirst = newNextFirst;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (size <= 0.3 * items.length) {
            resize(size * 2);
        }
        int newNextLast = (nextLast - 1 + items.length) % items.length;
        T last = items[newNextLast];
        items[newNextLast] = null;
        nextLast = newNextLast;
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            int arrayIndex = (nextFirst + 1 + index + items.length) % items.length;
            return items[arrayIndex];
        }
    }
}
