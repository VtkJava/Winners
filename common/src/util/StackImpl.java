package util;

public class StackImpl <E> implements Stack<E> {

    public static final int DEFAULT_CAPACITY = 16;

    /**
     * Data storage to store the StackImpl's values
     */
    private E[] storage;

    /**
     * Index of the last element in the StackImpl.
     * Initial value must be -1.
     * This is not a length of data storage.
     * It must be increased when new element is added in to StackImpl
     * and decreased on pop action
     */
    private int tos;

    public StackImpl(int capacity) {
        this.storage = (E[])(new Object[capacity]);
        this.tos = -1;
    }

    public StackImpl() {
        this(DEFAULT_CAPACITY);
    }

    public void push(E value) {
        if(tos == storage.length-1) {
            insureCapacity();
        }
        storage[++tos] = value;

    }

    public E pop() {
        if(tos == -1){
            return null;
        }
        return (E) storage[tos--];
    }

    @Override
    public boolean isEmpty() {
        return tos == -1;
    }

    @Override
    public void reset() {
        tos = -1;
        clear();
    }

    private void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    private void insureCapacity() {
        E [] extandCapacity = (E[]) new Object[storage.length *  3 / 2 + 1];
        for (int i = 0; i <storage.length ; i++) {
            extandCapacity[i]  = storage[i];
        }
        storage = extandCapacity;
    }

}
