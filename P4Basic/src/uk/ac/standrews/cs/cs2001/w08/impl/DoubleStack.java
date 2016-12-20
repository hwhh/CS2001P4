package uk.ac.standrews.cs.cs2001.w08.impl;


import uk.ac.standrews.cs.cs2001.w08.interfaces.IDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

/**
 * Double stack class.
 */
public class DoubleStack implements IDoubleStack {

    private static final int DEFAULT_SIZE = 10;
    /**
     * ID's for the stacks.
     */
    private static final int ID1 = 1;
    private static final int ID2 = 2;
    /**
     *Max size of the array used by both stacks.
     */
    public int maxSize;
    /**
     * The array used by both stacks.
     */
    private Object[] doubleStackArray;
    /**
     * Both stacks.
     */
    private IStack iStack1, iStack2;

    /**
     * Constructor sets default size.
     */
    public DoubleStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor creates both stacks.
     * @param maxSize of array
     */
    public DoubleStack(int maxSize) {
        this.maxSize = maxSize;
        //Initialise the array
        doubleStackArray = new Object[maxSize];
        //Create the first and second stack with correct ID's
        iStack1 = new Stack(ID1, doubleStackArray);
        iStack2 = new Stack(ID2, doubleStackArray);
    }

    /**
     * Get the first stack.
     *
     * @return
     */
    @Override
    public IStack getFirstStack() {
        return iStack1;
    }

    /**
     * Get the second stack.
     *
     * @return
     */
    @Override
    public IStack getSecondStack() {
        return iStack2;
    }
}
