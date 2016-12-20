package uk.ac.standrews.cs.cs2001.w08.testExtentions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.extention.PriorityQueueUsingDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.extention.PriorityQueueUsingLinkedList;
import uk.ac.standrews.cs.cs2001.w08.impl.PriorityQueue;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * This class is used to test the functionality of every priority queue class implantation.
 */
@RunWith(Parameterized.class)
public class PriorityQueueTests {

    /**
     * The iPriorityQueue object for each class.
     */
    IPriorityQueue iPriorityQueue;
    /**
     * Current class being tested.
     */
    private Class iPriorityQueueClass;

    /**
     * Constructor.
     *
     * @param cl the current class being tested
     */
    public PriorityQueueTests(Class cl) {
        iPriorityQueueClass = cl;
    }

    /**
     * Create a collection containing every priority class implantation.
     *
     * @return a collection containing every priority class implantation.
     * @throws NoSuchMethodException
     */
    @Parameterized.Parameters
    public static Collection classesAndMethods() throws NoSuchMethodException {
        List<Class> list = new ArrayList<>();
        list.add(PriorityQueue.class);
        list.add(PriorityQueueUsingDoubleStack.class);
        list.add(PriorityQueueUsingLinkedList.class);
        return list;
    }

    /**
     * Set the iPriorityQueue attribute to and implantation of the current class being tested
     */
    @Before
    public void setUp() {
        try {
            //Get a new instance of the current class
            iPriorityQueue = (IPriorityQueue) iPriorityQueueClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test enqueue.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testEnqueue() throws QueueFullException, QueueEmptyException {
        //Enqueue 11, check dequeue return right number and size
        iPriorityQueue.enqueue(11);
        assertEquals(iPriorityQueue.size(), 1);
        assertEquals(iPriorityQueue.dequeue(), 11);

        //Enqueue two numbers then check dequeue in right order
        iPriorityQueue.enqueue(10);
        iPriorityQueue.enqueue(20);
        assertEquals(iPriorityQueue.size(), 2);
        assertEquals(iPriorityQueue.dequeue(), 20);
        assertEquals(iPriorityQueue.dequeue(), 10);

        //Enqueue 10 numbers then check dequeue in right order and size
        iPriorityQueue.enqueue(10);
        iPriorityQueue.enqueue(20);
        for (int x = 9; x < 17; x++) {
            iPriorityQueue.enqueue(x);
        }
        assertEquals(iPriorityQueue.size(), 10);
        assertEquals(iPriorityQueue.dequeue(), 20);
        assertEquals(iPriorityQueue.dequeue(), 16);
        assertEquals(iPriorityQueue.dequeue(), 15);
        assertEquals(iPriorityQueue.dequeue(), 14);
        assertEquals(iPriorityQueue.dequeue(), 13);
        assertEquals(iPriorityQueue.dequeue(), 12);
        assertEquals(iPriorityQueue.dequeue(), 11);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.dequeue(), 9);

        //Enqueue 10 numbers
        iPriorityQueue.enqueue(10);
        iPriorityQueue.enqueue(20);
        for (int x = 9; x < 17; x++) {
            iPriorityQueue.enqueue(x);
        }
        //Dequeue a number
        iPriorityQueue.dequeue();
        //Check size
        assertEquals(iPriorityQueue.size(), 9);
        //Check new enqueued item inserted right place and dequeue in right order and size
        iPriorityQueue.enqueue(-1);
        assertEquals(iPriorityQueue.dequeue(), 16);
        assertEquals(iPriorityQueue.dequeue(), 15);
        assertEquals(iPriorityQueue.dequeue(), 14);
        assertEquals(iPriorityQueue.dequeue(), 13);
        assertEquals(iPriorityQueue.dequeue(), 12);
        assertEquals(iPriorityQueue.dequeue(), 11);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.dequeue(), 9);
        assertEquals(iPriorityQueue.dequeue(), -1);
        assertEquals(iPriorityQueue.size(), 0);
    }

    /**
     * Check QueueFullException
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test(expected = QueueFullException.class)
    public void testEnqueueQueueFullException() throws QueueFullException, StackOverflowException, StackEmptyException, QueueEmptyException {
        //Try and enqueue 11 numbers
        for (int x = 0; x < 11; x++) {
            iPriorityQueue.enqueue(x);
        }
    }

    /**
     * Test dequeue.
     *
     * @throws QueueEmptyException
     * @throws QueueFullException
     */
    @Test
    public void testDequeue() throws QueueEmptyException, QueueFullException, StackOverflowException, StackEmptyException {
        //Enqueue 10, check dequeue return right number and size
        iPriorityQueue.enqueue(10);
        assertEquals(iPriorityQueue.size(), 1);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.size(), 0);

        //Enqueue -1, check dequeue return right number and size
        iPriorityQueue.enqueue(-1);
        assertEquals(iPriorityQueue.dequeue(), -1);
        assertEquals(iPriorityQueue.size(), 0);

        //Enqueue two numbers then check dequeue in right order and size
        iPriorityQueue.enqueue(20);
        iPriorityQueue.enqueue(-1);
        assertEquals(iPriorityQueue.size(), 2);
        assertEquals(iPriorityQueue.dequeue(), 20);
        assertEquals(iPriorityQueue.dequeue(), -1);
        iPriorityQueue.enqueue(20);
        iPriorityQueue.enqueue(-1);

        //Enqueue 10 numbers then check dequeue in right order
        for (int x = 9; x < 17; x++) {
            iPriorityQueue.enqueue(x);
        }
        assertEquals(iPriorityQueue.size(), 10);
        assertEquals(iPriorityQueue.dequeue(), 20);
        assertEquals(iPriorityQueue.dequeue(), 16);
        assertEquals(iPriorityQueue.dequeue(), 15);
        assertEquals(iPriorityQueue.dequeue(), 14);
        assertEquals(iPriorityQueue.dequeue(), 13);
        assertEquals(iPriorityQueue.dequeue(), 12);
        assertEquals(iPriorityQueue.dequeue(), 11);
        assertEquals(iPriorityQueue.dequeue(), 10);
        assertEquals(iPriorityQueue.dequeue(), 9);
        assertEquals(iPriorityQueue.dequeue(), -1);
        assertEquals(iPriorityQueue.size(), 0);

        //Enqueue 8 0s
        for (int x = 0; x < 8; x++) {
            iPriorityQueue.enqueue(0);
        }
        //Enqueue 2 more numbers
        iPriorityQueue.enqueue(20);
        iPriorityQueue.enqueue(-1);
        //Check dequeue in right order and size
        assertEquals(iPriorityQueue.dequeue(), 20);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), 0);
        assertEquals(iPriorityQueue.dequeue(), -1);
        assertEquals(iPriorityQueue.size(), 0);

        //Enqueue 10 characters, check dequeue return right numbers in right order and size
        assertEquals(iPriorityQueue.size(), 0);
        Comparable[] test = new Comparable[]{'G', 'd', 'o', 'r', 'Q', 'a', 'F', 'X', '9', ' '};
        for (int x = 0; x < 10; x++) {
            iPriorityQueue.enqueue(test[x]);
        }
        assertEquals(iPriorityQueue.size(), 10);
        assertEquals(iPriorityQueue.dequeue(), 'r');
        assertEquals(iPriorityQueue.dequeue(), 'o');
        assertEquals(iPriorityQueue.dequeue(), 'd');
        assertEquals(iPriorityQueue.dequeue(), 'a');
        assertEquals(iPriorityQueue.dequeue(), 'X');
        assertEquals(iPriorityQueue.dequeue(), 'Q');
        assertEquals(iPriorityQueue.dequeue(), 'G');
        assertEquals(iPriorityQueue.dequeue(), 'F');
        assertEquals(iPriorityQueue.dequeue(), '9');
        assertEquals(iPriorityQueue.dequeue(), ' ');
        assertEquals(iPriorityQueue.size(), 0);
    }



}
