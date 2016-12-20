package uk.ac.standrews.cs.cs2001.w08.test;

import org.junit.Before;
import org.junit.Test;
import uk.ac.standrews.cs.cs2001.w08.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.impl.PriorityQueue;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

import static org.junit.Assert.*;

/**
 * Tests priority queue implementation.
 */
public class ArrayPriorityQueueTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IPriorityQueue priorityQueue;

    @Before
    public void setUp() throws Exception {
        this.priorityQueue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
    }

    /**
     * Tests that the factory constructs a non-null priority queue.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makePriorityQueue returns null, expected non-null PriorityQueue object", queue != null);
    }

    /**
     * Test enqueue.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testEnqueue() throws QueueFullException, QueueEmptyException {
        //Enqueue 10, check size and array
        priorityQueue.enqueue(10);
        assertEquals(priorityQueue.size(), 1);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{10, null, null, null, null, null, null, null, null, null});

        //Enqueue 20, check size and array
        priorityQueue.enqueue(20);
        assertEquals(priorityQueue.size(), 2);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{10, 20, null, null, null, null, null, null, null, null});

        //Enqueue further 8 numbers, check size and array
        for (int x = 9; x < 17; x++) {
            priorityQueue.enqueue(x);
        }
        assertEquals(priorityQueue.size(), 10);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{10, 20, 9, 10, 11, 12, 13, 14, 15, 16});

        //Dequeue then check size
        priorityQueue.dequeue();
        assertEquals(priorityQueue.size(), 9);

        //Enqueue -1 check size and array
        priorityQueue.enqueue(-1);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{10, 16, 9, 10, 11, 12, 13, 14, 15, -1});
        assertEquals(priorityQueue.size(), 10);
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
            priorityQueue.enqueue(x);
        }
    }

    /**
     * Test dequeue.
     *
     * @throws QueueEmptyException
     * @throws QueueFullException
     */
    @Test
    public void testDequeue() throws QueueEmptyException, QueueFullException {
        //Enqueue 10, check size and array
        priorityQueue.enqueue(10);
        assertEquals(priorityQueue.size(), 1);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{10, null, null, null, null, null, null, null, null, null});

        //Dequeue then check size
        priorityQueue.dequeue();
        assertEquals(priorityQueue.size(), 0);
        //Enqueue -1, check array
        priorityQueue.enqueue(-1);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{-1, null, null, null, null, null, null, null, null, null});

        //Enqueue 20, check size and array
        priorityQueue.enqueue(20);
        assertEquals(priorityQueue.size(), 2);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{-1, 20, null, null, null, null, null, null, null, null});
        //Enqueue further 8 numbers, check size and array
        for (int x = 9; x < 17; x++) {
            priorityQueue.enqueue(x);
        }
        assertEquals(priorityQueue.size(), 10);
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{-1, 20, 9, 10, 11, 12, 13, 14, 15, 16});

        //Dequeue 7 numbers and check size is correct
        for (int x = 0; x < 7; x++) {
            priorityQueue.dequeue();
        }
        assertEquals(priorityQueue.size(), 3);

        //Enqueue 7 0's
        for (int x = 0; x < 7; x++) {
            priorityQueue.enqueue(0);
        }
        //Check that the right numbers where removed, biggest numbers should have been removed
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{-1, 10, 9, 0, 0, 0, 0, 0, 0, 0});

        //Dequeue all elements
        for (int x = 0; x < 10; x++) {
            priorityQueue.dequeue();
        }
        //Enqueue 10 A's check size and array
        assertEquals(priorityQueue.size(), 0);
        for (int x = 0; x < 10; x++) {
            priorityQueue.enqueue('A');
        }
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});
        assertEquals(priorityQueue.size(), 10);

        //Clear queue, queue chars and numbers in random order
        priorityQueue.clear();
        priorityQueue.enqueue('D');
        priorityQueue.enqueue('E');
        priorityQueue.enqueue('3');
        priorityQueue.enqueue('G');
        priorityQueue.enqueue('1');
        priorityQueue.enqueue('B');
        priorityQueue.enqueue('A');
        priorityQueue.enqueue('2');
        priorityQueue.enqueue('F');
        priorityQueue.enqueue('C');
        //Dequeue 4 element's and enqueue 4 new ones
        priorityQueue.dequeue();
        priorityQueue.dequeue();
        priorityQueue.dequeue();
        priorityQueue.dequeue();
        priorityQueue.enqueue('W');
        priorityQueue.enqueue('X');
        priorityQueue.enqueue('Y');
        priorityQueue.enqueue('Z');
        //Check the elements with highest priority are removed and order of queue is preserved
        assertArrayEquals(((PriorityQueue) priorityQueue).queue, new Object[]{'A', '2', '3', 'C', '1', 'B', 'W', 'X', 'Y', 'Z'});
    }

    /**
     * Check for QueueEmptyException.
     *
     * @throws QueueEmptyException
     * @throws QueueFullException
     */
    @Test(expected = QueueEmptyException.class)
    public void testDequeueQueueEmptyException() throws QueueEmptyException, QueueFullException {
        //Try and dequeue when queue empty
        priorityQueue.dequeue();
        priorityQueue.enqueue(20);
        priorityQueue.dequeue();
        priorityQueue.dequeue();
    }


    /**
     * Test size.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testSize() throws QueueFullException, QueueEmptyException {
        //Check initially size is 0
        assertEquals(priorityQueue.size(), 0);

        //Enqueue two items and check size is 2
        priorityQueue.enqueue(10);
        priorityQueue.enqueue(10);
        assertEquals(priorityQueue.size(), 2);

        //Remove 1 item and check size is 1
        priorityQueue.dequeue();
        assertEquals(priorityQueue.size(), 1);

        //Add 9 more items and check size is 10
        for (int x = 0; x < 9; x++) {
            priorityQueue.enqueue(x);
        }
        assertEquals(priorityQueue.size(), 10);

        //Remove all items and check size is 0
        for (int x = 0; x < 10; x++) {
            priorityQueue.dequeue();
        }
        assertEquals(priorityQueue.size(), 0);
    }

    /**
     * Test if queue detects empty.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testIsEmpty() throws QueueFullException, QueueEmptyException {
        //Check initially size is 0
        assertTrue(priorityQueue.isEmpty());

        //Enqueue an items and check queue is no longer empty
        priorityQueue.enqueue(10);
        assertTrue(!priorityQueue.isEmpty());

        //Dequeue an items and check queue is now empty
        priorityQueue.dequeue();
        assertTrue(priorityQueue.isEmpty());

        //Enqueue 10 items and check queue is not empty
        for (int x = 0; x < 9; x++) {
            priorityQueue.enqueue('A');
        }
        assertTrue(!priorityQueue.isEmpty());

        //Dequeue all 10 items and check queue is now empty
        for (int x = 0; x < 9; x++) {
            priorityQueue.dequeue();
        }
        assertTrue(priorityQueue.isEmpty());
    }

    /**
     * Test clear.
     *
     * @throws QueueFullException
     * @throws QueueEmptyException
     */
    @Test
    public void testClear() throws QueueFullException, QueueEmptyException {
        //Check initially size is 0
        assertEquals(priorityQueue.size(), 0);
        //Enqueue an items and check queue is no longer empty
        priorityQueue.enqueue(10);
        assertEquals(priorityQueue.size(), 1);
        //Clear queue and check size is again 0
        priorityQueue.clear();
        assertEquals(priorityQueue.size(), 0);

        //Try and clear already empty queue
        priorityQueue.enqueue(10);
        priorityQueue.dequeue();
        priorityQueue.clear();
        assertEquals(priorityQueue.size(), 0);

        //Fill up queue
        for (int x = 9; x >= 0; x--) {
            priorityQueue.enqueue(x);
        }
        //Check is full then clear and check is empty
        assertEquals(priorityQueue.size(), 10);
        priorityQueue.clear();
        assertEquals(priorityQueue.size(), 0);
    }

}
