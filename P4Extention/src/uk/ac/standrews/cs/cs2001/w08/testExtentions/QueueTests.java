package uk.ac.standrews.cs.cs2001.w08.testExtentions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import uk.ac.standrews.cs.cs2001.w08.common.*;
import uk.ac.standrews.cs.cs2001.w08.extention.PriorityQueueUsingDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.extention.PriorityQueueUsingLinkedList;
import uk.ac.standrews.cs.cs2001.w08.extention.QueueUsingDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.impl.PriorityQueue;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to test the functionality of every queue class implantation.
 */
@RunWith(Parameterized.class)
public class QueueTests extends AbstractFactoryClient {

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
    public QueueTests(Class cl) {
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
        list.add(QueueUsingDoubleStack.class);
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
     * Check for QueueEmptyException.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test(expected = QueueFullException.class)
    public void testEnqueueQueueFullException() throws QueueFullException, QueueEmptyException {
        //Try and enqueue 11 numbers
        for (int x = 0; x < 11; x++) {
            iPriorityQueue.enqueue(x);
        }
    }

    /**
     * Check for QueueEmptyException.
     *
     * @throws QueueEmptyException
     * @throws QueueFullException
     */
    @Test(expected = QueueEmptyException.class)
    public void testDequeueQueueEmptyException() throws QueueEmptyException, QueueFullException, StackEmptyException, StackOverflowException {
        //Try and dequeue when queue empty
        iPriorityQueue.dequeue();
        iPriorityQueue.enqueue(20);
        iPriorityQueue.dequeue();
        iPriorityQueue.dequeue();
    }


    @Test
    public void testSize() throws QueueFullException, QueueEmptyException, StackOverflowException, StackEmptyException {
        //Check initially size is 0
        assertEquals(iPriorityQueue.size(), 0);

        //Enqueue two items and check size is 2
        iPriorityQueue.enqueue(11);
        iPriorityQueue.enqueue(11);
        assertEquals(iPriorityQueue.size(), 2);

        //Remove 1 item and check size is 1
        iPriorityQueue.dequeue();
        assertEquals(iPriorityQueue.size(), 1);

        //Add 9 more items and check size is 10
        for (int x = 0; x < 9; x++) {
            iPriorityQueue.enqueue(x);
        }
        assertEquals(iPriorityQueue.size(), 10);

        //Remove all items and check size is 0
        for (int x = 0; x < 10; x++) {
            iPriorityQueue.dequeue();
        }
        assertEquals(iPriorityQueue.size(), 0);
    }

    /**
     * Test if queue detects empty.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testIsEmpty() throws QueueFullException, QueueEmptyException, StackOverflowException, StackEmptyException {
        //Check initially size is 0
        assertTrue(iPriorityQueue.isEmpty());

        //Enqueue an items and check queue is no longer empty
        iPriorityQueue.enqueue(11);
        assertTrue(!iPriorityQueue.isEmpty());

        //Dequeue an items and check queue is now empty
        iPriorityQueue.dequeue();
        assertTrue(iPriorityQueue.isEmpty());

        //Enqueue 10 items and check queue is not empty
        for (int x = 0; x < 9; x++) {
            iPriorityQueue.enqueue('A');
        }
        assertTrue(!iPriorityQueue.isEmpty());

        //Dequeue all 10 items and check queue is now empty
        for (int x = 0; x < 9; x++) {
            iPriorityQueue.dequeue();
        }
        assertTrue(iPriorityQueue.isEmpty());
    }


    /**
     * Test clear.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testClear() throws QueueFullException, QueueEmptyException, StackOverflowException, StackEmptyException {
        //Check initially size is 0
        assertEquals(iPriorityQueue.size(), 0);
        //Enqueue an items and check queue is no longer empty
        iPriorityQueue.enqueue(11);
        assertEquals(iPriorityQueue.size(), 1);
        //Clear queue and check size is again 0
        iPriorityQueue.clear();
        assertEquals(iPriorityQueue.size(), 0);

        //Try and clear already empty queue
        iPriorityQueue.enqueue(10);
        iPriorityQueue.dequeue();
        iPriorityQueue.clear();
        assertEquals(iPriorityQueue.size(), 0);

        //Fill up queue
        for (int x = 9; x >= 0; x--) {
            iPriorityQueue.enqueue(x);
        }

        //Check is full then clear and check is empty
        assertEquals(iPriorityQueue.size(), 10);
        iPriorityQueue.clear();
        assertEquals(iPriorityQueue.size(), 0);
    }
}
