package uk.ac.standrews.cs.cs2001.w08.extention;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.impl.DoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;


/**
 * PriorityQueue class.
 */
public class QueueUsingDoubleStack implements IPriorityQueue {

    private static final int ZERO = 0;
    private static final int DEFAULT_SIZE = 10;
    /**
     * Array of object type comparable.
     */
    private DoubleStack doubleStack;
    /**
     * size of the queue.
     */
    private int size, index;

    /**
     * Constructor sets default size.
     */
    public QueueUsingDoubleStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor, create new double stack and set index and size attributes.
     */
    public QueueUsingDoubleStack(int maxSize) {
        doubleStack = new DoubleStack(maxSize);
        this.size = doubleStack.maxSize;
        this.index = ZERO;
    }

    /**
     * Add an item to the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException
     */
    @Override
    public void enqueue(Comparable element) throws QueueFullException {
        //Check if stack is full
        if (index == size) {
            throw new QueueFullException();
        }
        try {
            //Add new element to first queue
            doubleStack.getFirstStack().push(element);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
        //Increment the index
        index++;
    }

    /**
     * Remove an item to the queue. First stack used as holding stack, which is emptied into the second stack, reversing the order
     * Second stack is ordered and used as the queue.
     *
     * @return Comparable: the item removed from the queue.
     * @throws QueueEmptyException
     */
    @Override
    public Comparable dequeue() throws QueueEmptyException {
        //Check is queue is empty
        Comparable temp = null;
        if (index == ZERO) {
            throw new QueueEmptyException();
        }
        try {
            //Check if first stack is empty
            if (doubleStack.getSecondStack().isEmpty()) {
                //Empty the first stack into the second stack
                while (!doubleStack.getFirstStack().isEmpty()) {
                    doubleStack.getSecondStack().push(doubleStack.getFirstStack().pop());
                }
            }
            //Check if the second stack is empty
            if (!doubleStack.getSecondStack().isEmpty()) {
                //Remove the item from the top of the second stack
                temp = (Comparable) doubleStack.getSecondStack().pop();
                index--;
            }

        } catch (StackEmptyException | StackOverflowException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Return the size of the queue.
     *
     * @return int: the size of the queue.
     */
    @Override
    public int size() {
        return index;
    }

    /**
     * Return whether queue is empty.
     *
     * @return boolean: whether queue is empty
     */
    @Override
    public boolean isEmpty() {
        return index == ZERO;
    }

    /**
     * Clear the queue.
     */
    @Override
    public void clear() {
        //Clear both stacks and reset the index
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
        index = ZERO;
    }


}


