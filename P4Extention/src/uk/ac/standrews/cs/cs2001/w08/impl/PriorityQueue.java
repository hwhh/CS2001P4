package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

/**
 * PriorityQueue class.
 */
public class PriorityQueue implements IPriorityQueue {

    private static final int ZERO = 0;
    private static final int DEFAULT_SIZE = 10;


    /**
     * Array of object type comparable.
     */
    public Comparable[] queue;
    /**
     * Current index in queue and size.
     */
    private int index, size;

    /**
     * Constructor sets default size.
     */
    public PriorityQueue() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param maxSize of the queue.
     */
    public PriorityQueue(int maxSize) {
        queue = new Comparable[maxSize];
        this.index = ZERO;
        size = maxSize;
    }

    /**
     * Add an item to the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException
     */
    @Override
    public void enqueue(Comparable element) throws QueueFullException {
        //If the queue is full
        if (index == size) {
            throw new QueueFullException();
        }
        //Add new element to next place in queue
        queue[index] = element;
        //Increment index
        index++;
    }

    /**
     * Remove the item with the highest priority from the queue.
     *
     * @return Comparable: the item removed from the queue.
     * @throws QueueEmptyException
     */
    @Override
    public Comparable dequeue() throws QueueEmptyException {
        //If the queue is empty
        if (index == ZERO) {
            throw new QueueEmptyException();
        }
        int largestElementIndex = 0;
        Comparable result;
        //Traverse the array to find the largest element
        for (int i = 1; i < index; i++) {
            if (queue[i].compareTo(queue[largestElementIndex]) > ZERO) {
                //Store index of the largest element
                largestElementIndex = i;
            }
        }
        result = queue[largestElementIndex];
        //Decrement the index
        index--;
        //Move largest item to un-indexed location in array
        queue[largestElementIndex] = queue[index];
        return result;
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
        index = ZERO;
    }


}





