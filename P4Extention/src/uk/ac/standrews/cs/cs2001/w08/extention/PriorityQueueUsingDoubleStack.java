package uk.ac.standrews.cs.cs2001.w08.extention;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.impl.DoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

/**
 * PriorityQueueUsingDoubleStack class.
 */
public class PriorityQueueUsingDoubleStack implements IPriorityQueue {

    private static final int ZERO = 0;
    private static final int DEFAULT_SIZE = 10;

    /**
     * Stacks accessed through double stack class.
     */
    public DoubleStack doubleStack;
    /**
     * size of the queue.
     */
    private int size, index;

    /**
     * Constructor sets default size.
     */
    public PriorityQueueUsingDoubleStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor, create new double stack and set index and size attributes.
     */
    public PriorityQueueUsingDoubleStack(int maxSize) {
        doubleStack = new DoubleStack(maxSize);
        this.size = doubleStack.maxSize;
        index = ZERO;
    }

    /**
     * Add an item to the queue. First stack used as temporary stack enabling ordering, the second stack acts as the queue.
     * Second stack is ordered and used as the queue.
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
            //If the queue contains any elements
            if (!doubleStack.getSecondStack().isEmpty()) {
                //Find the correct location in the second stack for the new element
                while ((!doubleStack.getSecondStack().isEmpty()) && (element.compareTo(doubleStack.getSecondStack().top()) < ZERO)) {
                    //Put item from queue to temporary stack until element is in the right place in queue
                    doubleStack.getFirstStack().push(doubleStack.getSecondStack().pop());
                }
                //Add the new element to the queue
                doubleStack.getSecondStack().push(element);
            } else {
                //If queue empty add element
                doubleStack.getSecondStack().push(element);
            }
            while (!doubleStack.getFirstStack().isEmpty()) {
                //Move elements from temporary stack back to queue after new element added
                doubleStack.getSecondStack().push(doubleStack.getFirstStack().pop());
            }
            //Increment the index
            index++;
        } catch (StackEmptyException | StackOverflowException e) {
            e.printStackTrace();
        }

    }

    /**
     * Remove the item with the highest priority from the queue.
     *
     * @return Comparable: the item removed from the queue.
     * @throws QueueEmptyException
     */
    @Override
    public Comparable dequeue() throws QueueEmptyException {
        //Check if stack is empty
        if (index <= ZERO) {
            throw new QueueEmptyException();
        } else {
            //Create new object to be removed
            Comparable temp = null;
            try {
                //Remove the item from the front of the queue
                temp = (Comparable) doubleStack.getSecondStack().pop();
            } catch (StackEmptyException e) {
                e.printStackTrace();
            }
            //Decrement the index
            index--;
            return temp;
        }
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





