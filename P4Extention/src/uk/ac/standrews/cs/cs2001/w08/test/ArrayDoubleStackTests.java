package uk.ac.standrews.cs.cs2001.w08.test;


import org.junit.Before;
import org.junit.Test;
import uk.ac.standrews.cs.cs2001.w08.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.impl.Stack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IDoubleStack;

import static org.junit.Assert.*;

/**
 * Tests array collection implementation.
 */
public class ArrayDoubleStackTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IDoubleStack doubleStack;


    /**
     * Set up before each test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
    }

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makeDoubleStack returns null, expected non-null IDoubleStack object", doubleStack1 != null);
    }

    /**
     * Test push.
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testPush() throws StackOverflowException, StackEmptyException {
        //Check stack empty
        assertEquals(doubleStack.getFirstStack().size(), 0);
        //Push 10, check stack is no longer empty and 10 is at top of stack
        doubleStack.getFirstStack().push(10);
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getFirstStack().top(), 10);

        //Check stack array
        assertArrayEquals(Stack.elements, new Object[]{10, null, null, null, null, null, null, null, null, null});

        //Push 20 to second stack, check stack size and 20 is at top of stack
        doubleStack.getSecondStack().push(20);
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().top(), 20);
        //Check array is filling in correct directions
        assertArrayEquals(Stack.elements, new Object[]{10, null, null, null, null, null, null, null, null, 20});

        //Push 7 numbers to second stack
        for (int x = 15; x > 8; x--) {
            doubleStack.getSecondStack().push(x);
        }
        //Check size of both stacks
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 8);
        //Check array is filling in correct directions
        assertArrayEquals(Stack.elements, new Object[]{10, null, 9, 10, 11, 12, 13, 14, 15, 20});

        //Push 11 to stack 1 and check sizes of stacks
        doubleStack.getFirstStack().push(11);
        assertEquals(doubleStack.getFirstStack().size(), 2);
        assertEquals(doubleStack.getSecondStack().size(), 8);
        //Check array is filling in correct directions
        assertArrayEquals(Stack.elements, new Object[]{10, 11, 9, 10, 11, 12, 13, 14, 15, 20});

        //Pop item from first stack and push new item
        doubleStack.getFirstStack().pop();
        doubleStack.getFirstStack().push(12);
        //Check new item in correct position
        assertArrayEquals(Stack.elements, new Object[]{10, 12, 9, 10, 11, 12, 13, 14, 15, 20});

        //Clear both stacks
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
        //Check both stacks cleared
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);
        //Add 10 'A's to fist stack
        for (int x = 0; x < 10; x++) {
            doubleStack.getFirstStack().push('A');
        }
        //Check array and stack sizes
        assertArrayEquals(Stack.elements, new Object[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});
        assertEquals(doubleStack.getFirstStack().size(), 10);
        assertEquals(doubleStack.getSecondStack().size(), 0);
    }


    /**
     * Check StackOverflowException.
     *
     * @throws StackOverflowException
     */
    @Test(expected = StackOverflowException.class)
    public void testPushStackEmptyException() throws StackOverflowException {
        //Try to push 11 elements to stack
        for (int x = 0; x < 11; x++) {
            doubleStack.getFirstStack().push(x);
        }
    }

    /**
     * Test pop.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test
    public void testPop() throws StackEmptyException, StackOverflowException {
        //Push 10, check size and array
        doubleStack.getFirstStack().push(10);
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertArrayEquals(Stack.elements, new Object[]{10, null, null, null, null, null, null, null, null, null});

        //Push 20 to second stack, check stack size and array
        doubleStack.getSecondStack().push(20);
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 1);
        //Check array is filling in correct directions
        assertArrayEquals(Stack.elements, new Object[]{10, null, null, null, null, null, null, null, null, 20});

        //Pop elements from both stacks then check sizes
        doubleStack.getFirstStack().pop();
        doubleStack.getSecondStack().pop();
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Push 9 items to second stack
        for (int x = 9; x >= 0; x--) {
            doubleStack.getSecondStack().push(x);
        }
        //Check size and array is filling in correct directions
        assertEquals(doubleStack.getSecondStack().size(), 10);
        assertArrayEquals(Stack.elements, new Object[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});

        //Pop item from stack, check size
        doubleStack.getSecondStack().pop();
        assertEquals(doubleStack.getSecondStack().size(), 9);
        //Push new items and check array is filling in correct directions
        doubleStack.getFirstStack().push(-1);
        assertArrayEquals(Stack.elements, new Object[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        doubleStack.getSecondStack().pop();
        doubleStack.getFirstStack().push(-2);
        assertArrayEquals(Stack.elements, new Object[]{-1, -2, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    /**
     * Check for StackEmptyException.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test(expected = StackEmptyException.class)
    public void testPopStackEmptyException() throws StackEmptyException, StackOverflowException {
        //Try and pop when stack empty
        doubleStack.getFirstStack().pop();
        doubleStack.getSecondStack().push(10);
        doubleStack.getSecondStack().pop();
        doubleStack.getSecondStack().pop();
        doubleStack.getFirstStack().pop();
    }


    /**
     * Test top.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test
    public void testTop() throws StackEmptyException, StackOverflowException {
        //Push 10 and check at top
        doubleStack.getFirstStack().push(10);
        assertEquals(doubleStack.getFirstStack().top(), 10);

        //Pop more numbers check new top
        doubleStack.getFirstStack().push(9);
        doubleStack.getFirstStack().push(8);
        assertEquals(doubleStack.getFirstStack().top(), 8);

        //Pop items check 10 now at top again
        doubleStack.getFirstStack().pop();
        doubleStack.getFirstStack().pop();
        assertEquals(doubleStack.getFirstStack().top(), 10);

        //Check top of both stacks
        doubleStack.getSecondStack().push(1);
        assertEquals(doubleStack.getSecondStack().top(), 1);
        assertEquals(doubleStack.getFirstStack().top(), 10);

        //Pop more numbers to the second stack and check new top
        doubleStack.getSecondStack().push(2);
        doubleStack.getSecondStack().push(3);
        assertEquals(doubleStack.getSecondStack().top(), 3);
        assertEquals(doubleStack.getFirstStack().top(), 10);

        //Pop items check new tops
        doubleStack.getSecondStack().pop();
        doubleStack.getSecondStack().pop();
        assertEquals(doubleStack.getSecondStack().top(), 1);
        assertEquals(doubleStack.getFirstStack().top(), 10);
    }

    /**
     * Check StackEmptyException.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test(expected = StackEmptyException.class)
    public void testTopStackEmptyException() throws StackEmptyException, StackOverflowException {
        //Get top when stack empty
        doubleStack.getFirstStack().top();
        doubleStack.getSecondStack().top();
        //Add items then remove them
        doubleStack.getFirstStack().push(10);
        doubleStack.getFirstStack().push(10);
        doubleStack.getSecondStack().pop();
        doubleStack.getSecondStack().pop();
        doubleStack.getFirstStack().top();
        doubleStack.getSecondStack().top();

    }

    /**
     * Test size.
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testSize() throws StackOverflowException, StackEmptyException {
        //Check both stacks are empty
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Push number to first stack stacks
        doubleStack.getFirstStack().push(10);
        doubleStack.getFirstStack().push(10);
        //Check both stacks return the correct size
        assertEquals(doubleStack.getFirstStack().size(), 2);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Remove item from first stack and check both stacks return the correct size
        doubleStack.getFirstStack().pop();
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Fill stack and check both stacks return the correct size
        for (int x = 8; x >= 0; x--) {
            doubleStack.getSecondStack().push(x);
        }
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 9);
    }

    /**
     * Test isEmpty.
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testIsEmpty() throws StackOverflowException, StackEmptyException {
        //Check stack is empty initially
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());

        //Push 10 to both stacks then check both are now not empty
        doubleStack.getFirstStack().push(10);
        doubleStack.getSecondStack().push(10);
        assertTrue(!doubleStack.getFirstStack().isEmpty());
        assertTrue(!doubleStack.getSecondStack().isEmpty());

        //Clear both stacks
        doubleStack.getFirstStack().pop();
        doubleStack.getSecondStack().pop();
        //Push element to first stack, check first stack not empty and second is
        doubleStack.getFirstStack().push(10);
        assertTrue(!doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());

        //Push element to second stack, check second stack not empty and first is
        doubleStack.getFirstStack().pop();
        doubleStack.getSecondStack().push(10);
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(!doubleStack.getSecondStack().isEmpty());

        //Clear both stacks and check there both empty
        doubleStack.getSecondStack().pop();
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());

        //Fill first stack and check first stack not empty and second is
        for (int x = 0; x < 9; x++) {
            doubleStack.getFirstStack().push('A');
        }
        assertTrue(!doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());

        //Clear first stack and check both stacks are empty
        for (int x = 0; x < 9; x++) {
            doubleStack.getFirstStack().pop();
        }
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Test clear,
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testClear() throws StackOverflowException, StackEmptyException {
        //Check initially size of both stacks is 0
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Push 10 to both stacks, check size
        doubleStack.getFirstStack().push(10);
        doubleStack.getSecondStack().push(10);
        assertEquals(doubleStack.getFirstStack().size(), 1);
        assertEquals(doubleStack.getSecondStack().size(), 1);

        //Clear first stack and check only first stack no empty
        doubleStack.getFirstStack().clear();
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 1);

        //Try and clear already empty stack
        doubleStack.getSecondStack().push(10);
        doubleStack.getSecondStack().pop();
        doubleStack.getSecondStack().pop();
        doubleStack.getSecondStack().clear();
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);

        //Fill second stack and check its full
        for (int x = 9; x >= 0; x--) {
            doubleStack.getSecondStack().push(x);
        }
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 10);
        //Clear first stack and check second stack unaffected
        doubleStack.getFirstStack().clear();
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 10);

        //Clear second stack and make sure both stacks are empty
        doubleStack.getSecondStack().clear();
        assertEquals(doubleStack.getFirstStack().size(), 0);
        assertEquals(doubleStack.getSecondStack().size(), 0);
    }

}
