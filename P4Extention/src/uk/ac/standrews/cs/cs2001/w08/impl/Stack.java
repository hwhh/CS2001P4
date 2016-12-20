package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

/**
 * Stack class.
 */
public class Stack implements IStack {


    private static final int ZERO = 0;
    /**
     * The array used by both stacks.
     */
    public static Object[] elements;
    /**
     * Indexes used by the stacks.
     */
    private static int top1, top2, current_size, size;
    /**
     * ID for the stack.
     */
    private int ID;

    /**
     * Constructor.
     *
     * @param ID       Identifies which stack it is.
     * @param elements Shared array from Double StackUsingLinkedList class.
     */
    public Stack(int ID, Object[] elements) {
        //Set the array to the array in double stack class
        Stack.elements = elements;
        size = elements.length;
        this.ID = ID;
        //Set top1 to start of array and top2 to end of array
        current_size = ZERO;
        top1 = -1;
        top2 = size;
    }

    /**
     * Add a new object to the top of a specified stack.
     *
     * @param element the element to be pushed.
     * @throws StackOverflowException
     */
    @Override
    public void push(Object element) throws StackOverflowException {
        //Check if stack is full
        if (top2 == top1 + 1) {
            throw new StackOverflowException();
        }
        //Check ID of stack using method
        if (ID == 1) {
            //Insert element at next free slot at start of array
            elements[++top1] = element;
        } else if (ID == 2) {
            //Insert element at next free slot at end of array
            elements[--top2] = element;
        }
        current_size++;
    }

    /**
     * Remove the element at the top of a specified stack.
     *
     * @return Object: the removed element.
     * @throws StackEmptyException
     */
    @Override
    public Object pop() throws StackEmptyException {
        //Check ID of stack using method
        if (ID == 1) {
            //Check if first stack is empty
            if (top1 == -1) {
                throw new StackEmptyException();
            }
            //Decrement current size
            current_size--;
            //Remove last item added to first array
            return elements[top1--];
        } else if (ID == 2) {
            //Check if second stack is empty
            if (top2 == elements.length) {
                throw new StackEmptyException();
            }
            //Decrement current size
            current_size--;
            //Remove last item added to second array
            return elements[top2++];
        }
        return null;
    }

    /**
     * Get the element at the top of a specified stack stack.
     *
     * @return Object: The element at the top of a specified stack.
     * @throws StackEmptyException
     */
    @Override
    public Object top() throws StackEmptyException {
        //Check ID of stack using method
        if (ID == 1) {
            //Check if first stack is empty
            if (top1 == -1) {
                throw new StackEmptyException();
            }
            //Get last item added to first array
            return elements[top1];
        } else if (ID == 2) {
            //Check if second stack is empty
            if (top2 == elements.length) {
                throw new StackEmptyException();
            }
            //Get last item added to second array
            return elements[top2];
        }
        return null;
    }

    /**
     * Return the size of a specified stack stack.
     *
     * @return int: the sze of a specified stack.
     */
    @Override
    public int size() {
        //Check ID of stack using method
        if (ID == 1) {
            //Return the size of first array
            return top1 + 1;
        } else if (ID == 2) {
            //Return the size of first array
            return size - top2;
        }
        return current_size;
    }

    /**
     * Check if a specified stack is empty.
     *
     * @return boolean: status of a specified stack.
     */
    @Override
    public boolean isEmpty() {
        //Check ID of stack using method
        if (ID == 1) {
            //Check if first stack is empty
            return top1 == -1;
        } else if (ID == 2) {
            //Check if second stack is empty
            return top2 == size;
        }
        return true;
    }

    /**
     * Clear a specified stack.
     */
    @Override
    public void clear() {
        //Check ID of stack using method
        if (ID == 1) {
            //Reset first stack index
            top1 = -1;
        } else if (ID == 2) {
            //Reset second stack index
            top2 = size;
        }
        //Reset the current size of whole array
        current_size = ZERO;
    }

}
