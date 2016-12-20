package uk.ac.standrews.cs.cs2001.w08.extention;

import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

/**
 * StackUsingLinkedList class.
 */
public class StackUsingLinkedList implements IStack {

    private static final int ZERO = 0;
    /**
     * Size of the stack.
     */
    private int size;
    /**
     * Node at front of stack.
     */
    private Node first;

    /**
     * Constructor.
     */
    public StackUsingLinkedList() {
        size = ZERO;
        this.first = null;
    }

    /**
     * Add a new object to the top of a specified stack.
     *
     * @param element the element to be pushed.
     * @throws StackOverflowException
     */
    @Override
    public void push(Object element) throws StackOverflowException {
        //Get head of stack
        Node oldHead = first;
        //Head of stack set to new node
        first = new Node();
        //The pointer for the head is set to old head
        first.node = element;
        first.next = oldHead;
        //Increment size
        size++;
    }

    /**
     * Remove the element at the top of a specified stack.
     *
     * @return Object: the removed element.
     * @throws StackEmptyException
     */
    @Override
    public Object pop() throws StackEmptyException {
        //Check if stack is empty
        if (first == null) {
            throw new StackEmptyException();
        }
        //Get the data in the first node
        Object node = first.node;
        //Set the first node to next node in stack
        first = first.next;
        //Decrement size
        size--;
        return node;
    }

    /**
     * Get the element at the top of a specified stack stack.
     *
     * @return Object: The element at the top of a specified stack.
     * @throws StackEmptyException
     */
    @Override
    public Object top() throws StackEmptyException {
        if (first == null) {
            throw new StackEmptyException();
        }
        return first;
    }

    /**
     * Return the size of a specified stack stack.
     *
     * @return int: the sze of a specified stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if a specified stack is empty.
     *
     * @return boolean: status of a specified stack.
     */
    @Override
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * Clear a specified stack.
     */
    @Override
    public void clear() {
        first = null;
        size = ZERO;
    }

}
