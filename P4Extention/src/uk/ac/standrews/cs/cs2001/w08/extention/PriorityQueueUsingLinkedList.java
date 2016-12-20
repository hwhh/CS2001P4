package uk.ac.standrews.cs.cs2001.w08.extention;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

/**
 * PriorityQueueUsingLinkedList class.
 */
public class PriorityQueueUsingLinkedList implements IPriorityQueue {


    private static final int ZERO = 0;
    private static final int DEFAULT_SIZE = 10;
    /**
     * size of the queue and current index in stack.
     */
    private int size, index;
    /**
     * Front node in the stack, also the node with the largest value .
     */
    private Node front;

    /**
     * Constructor sets default size.
     */
    public PriorityQueueUsingLinkedList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor set index and size attributes.
     */
    public PriorityQueueUsingLinkedList(int size) {
        this.size = size;
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
        //If the queue is full
        if (index == size) {
            throw new QueueFullException();
        }
        //Get the new node at the front of the queue
        Node node = front;
        //Insert the element in the queue at the correct position
        front = insert(node, element);
        //Increment index
        index++;
    }

    /**
     * Called by enqueue, and used to insert the node into the correct position in the queue.
     *
     * @return Node: the item to be added.
     * @throws QueueEmptyException
     */
    private Node insert(Node node, Comparable element) {
        //Create a new node with the new element
        Node newNode = new Node(element);
        if (node == null) {
            //If the queue is empty insert new node at front
            return newNode;
        }
        //If the element is greater than the current node, insert
        if (element.compareTo(node.node) > 0) {
            newNode.next = node;
            node = newNode;
        } else {
            //Recurse until the correct position in the queue is found
            node.next = insert(node.next, element);
        }
        return node;
    }


    /**
     * Remove the item with the highest priority from the queue.
     *
     * @return Comparable: the item removed from the queue.
     * @throws QueueEmptyException
     */
    @Override
    public Comparable dequeue() throws QueueEmptyException {
        //Check is queue is empty
        if (index == ZERO) {
            throw new QueueEmptyException();
        }
        //Remove the node at the front of the queue
        Comparable nodeData = (Comparable) front.node;
        //Set the front node, to the node after it
        front = front.next;
        //Decrement index
        index--;
        return nodeData;

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
        return index == 0;
    }

    /**
     * Clear the queue.
     */
    @Override
    public void clear() {
        front = null;
        index = ZERO;
    }

}
