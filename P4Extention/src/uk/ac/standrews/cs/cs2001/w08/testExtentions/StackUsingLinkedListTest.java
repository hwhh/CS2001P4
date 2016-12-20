package uk.ac.standrews.cs.cs2001.w08.testExtentions;

import org.junit.Before;
import org.junit.Test;
import uk.ac.standrews.cs.cs2001.w08.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.extention.Node;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StackUsingLinkedListTest extends AbstractFactoryClient {

    private IStack stackUsingLinkedList;

    @Before
    public void setUp() throws Exception {
        this.stackUsingLinkedList = getFactory().makeStackUsingLinkedList();
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
        assertEquals(stackUsingLinkedList.size(), 0);
        //Push 10, check stack is no longer empty and 10 is at top of stack
        stackUsingLinkedList.push(10);
        assertEquals(stackUsingLinkedList.size(), 1);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 10);

        //Push 20, check size and 20 is now at top of stack
        stackUsingLinkedList.push(20);
        assertEquals(stackUsingLinkedList.size(), 2);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 20);
        assertEquals(stackUsingLinkedList.pop(), 20);
        assertEquals(stackUsingLinkedList.pop(), 10);

        //Push 10 numbers then check pop in right order
        stackUsingLinkedList.push(10);
        stackUsingLinkedList.push(20);
        for (int x = 15; x > 8; x--) {
            stackUsingLinkedList.push(x);
            //Check new numbers pushed are at top of stack
            assertEquals(((Node) stackUsingLinkedList.top()).node, x);
        }
        assertEquals(stackUsingLinkedList.size(), 9);
        stackUsingLinkedList.push(11);
        assertEquals(stackUsingLinkedList.size(), 10);
        assertEquals(stackUsingLinkedList.pop(), 11);
        assertEquals(stackUsingLinkedList.pop(), 9);
        assertEquals(stackUsingLinkedList.pop(), 10);
        assertEquals(stackUsingLinkedList.pop(), 11);
        assertEquals(stackUsingLinkedList.pop(), 12);
        assertEquals(stackUsingLinkedList.pop(), 13);
        assertEquals(stackUsingLinkedList.pop(), 14);
        assertEquals(stackUsingLinkedList.pop(), 15);
        assertEquals(stackUsingLinkedList.pop(), 20);
        assertEquals(stackUsingLinkedList.pop(), 10);
        stackUsingLinkedList.clear();

        //Push 10 characters, check pop return right character in right order and size
        assertEquals(stackUsingLinkedList.size(), 0);
        Object[] test = new Object[]{'G', 'd', 'o', 'r', 'Q', 'a', 'F', 'X', '9', ' '};
        for (int x = 0; x < 10; x++) {
            stackUsingLinkedList.push(test[x]);
        }
        assertEquals(stackUsingLinkedList.size(), 10);
        assertEquals(stackUsingLinkedList.pop(), ' ');
        assertEquals(stackUsingLinkedList.pop(), '9');
        assertEquals(stackUsingLinkedList.pop(), 'X');
        assertEquals(stackUsingLinkedList.pop(), 'F');
        assertEquals(stackUsingLinkedList.pop(), 'a');
        assertEquals(stackUsingLinkedList.pop(), 'Q');
        assertEquals(stackUsingLinkedList.pop(), 'r');
        assertEquals(stackUsingLinkedList.pop(), 'o');
        assertEquals(stackUsingLinkedList.pop(), 'd');
        assertEquals(stackUsingLinkedList.pop(), 'G');
        assertEquals(stackUsingLinkedList.size(), 0);
    }

    /**
     * Test pop.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test
    public void testPop() throws StackEmptyException, StackOverflowException {
        //Push 10 check size and top
        stackUsingLinkedList.push(10);
        assertEquals(stackUsingLinkedList.size(), 1);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 10);

        //Push 20 check size and top updated
        stackUsingLinkedList.push(20);
        assertEquals(stackUsingLinkedList.size(), 2);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 20);
        //Check pop return right numbers in right order
        assertEquals(stackUsingLinkedList.pop(), 20);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 10);
        assertEquals(stackUsingLinkedList.pop(), 10);
        assertEquals(stackUsingLinkedList.size(), 0);

        //Add 10 numbers to stack and check pop return right character in right order and size
        for (int x = 9; x >= 0; x--) {
            stackUsingLinkedList.push(x);
        }
        assertEquals(stackUsingLinkedList.size(), 10);
        assertEquals(stackUsingLinkedList.pop(), 0);
        assertEquals(stackUsingLinkedList.pop(), 1);
        assertEquals(stackUsingLinkedList.pop(), 2);
        assertEquals(stackUsingLinkedList.pop(), 3);
        assertEquals(stackUsingLinkedList.pop(), 4);
        assertEquals(stackUsingLinkedList.pop(), 5);
        assertEquals(stackUsingLinkedList.pop(), 6);
        assertEquals(stackUsingLinkedList.pop(), 7);
        assertEquals(stackUsingLinkedList.pop(), 8);
        assertEquals(stackUsingLinkedList.pop(), 9);
        assertEquals(stackUsingLinkedList.size(), 0);
    }

    /**
     * Check StackEmptyException.
     *
     * @throws StackEmptyException
     * @throws StackOverflowException
     */
    @Test(expected = StackEmptyException.class)
    public void testPopStackEmptyException() throws StackEmptyException, StackOverflowException {
        //Try and pop when stack empty
        stackUsingLinkedList.pop();
        stackUsingLinkedList.push(10);
        stackUsingLinkedList.pop();
        stackUsingLinkedList.pop();
        stackUsingLinkedList.pop();
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
        stackUsingLinkedList.push(10);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 10);

        //Pop more numbers check new top
        stackUsingLinkedList.push(9);
        stackUsingLinkedList.push(8);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 8);

        //Pop items check 10 now at top again
        stackUsingLinkedList.pop();
        stackUsingLinkedList.pop();
        assertEquals(((Node) stackUsingLinkedList.top()).node, 10);

        //Push 1 and check new top
        stackUsingLinkedList.push(1);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 1);

        //Pop more numbers check new top
        stackUsingLinkedList.push(2);
        stackUsingLinkedList.push(3);
        assertEquals(((Node) stackUsingLinkedList.top()).node, 3);

        //Pop items check 1 now at top again
        stackUsingLinkedList.pop();
        stackUsingLinkedList.pop();
        assertEquals(((Node) stackUsingLinkedList.top()).node, 1);
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
        assertEquals(stackUsingLinkedList.size(), 0);
        stackUsingLinkedList.top();
        //Add items then remove them
        stackUsingLinkedList.push(10);
        stackUsingLinkedList.push(10);
        stackUsingLinkedList.pop();
        stackUsingLinkedList.pop();
        stackUsingLinkedList.top();
    }

    @Test
    public void testSize() throws StackOverflowException, StackEmptyException {
        assertEquals(stackUsingLinkedList.size(), 0);
        assertEquals(stackUsingLinkedList.size(), 0);

        stackUsingLinkedList.push(10);
        stackUsingLinkedList.push(10);
        assertEquals(stackUsingLinkedList.size(), 2);

        stackUsingLinkedList.pop();
        assertEquals(stackUsingLinkedList.size(), 1);

        for (int x = 8; x >= 0; x--) {
            stackUsingLinkedList.push(x);
        }
        assertEquals(stackUsingLinkedList.size(), 10);
    }

    /**
     * Test size.
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testIsEmpty() throws StackOverflowException, StackEmptyException {
        //Check stack is empty
        assertTrue(stackUsingLinkedList.isEmpty());

        //Puh number to stack, check its not empty
        stackUsingLinkedList.push(10);
        assertTrue(!stackUsingLinkedList.isEmpty());

        //Pop item from stack check its empty again
        stackUsingLinkedList.pop();
        assertTrue(stackUsingLinkedList.isEmpty());

        //Puh number to stack, check its not empty again
        stackUsingLinkedList.push(10);
        assertTrue(!stackUsingLinkedList.isEmpty());

        //Pop item from stack check its empty again
        stackUsingLinkedList.pop();
        assertTrue(stackUsingLinkedList.isEmpty());
        //Fill stack
        for (int x = 0; x < 9; x++) {
            stackUsingLinkedList.push('A');
        }
        //Check stack not empty
        assertTrue(!stackUsingLinkedList.isEmpty());

        //Clear stack and check its empty
        for (int x = 0; x < 9; x++) {
            stackUsingLinkedList.pop();
        }
        assertTrue(stackUsingLinkedList.isEmpty());
    }

    /**
     * Test clear,
     *
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void testClear() throws StackOverflowException, StackEmptyException {
        //Check initially size of stacks is 0
        assertEquals(stackUsingLinkedList.size(), 0);

        //Push 10 to stack and check sie is 1
        stackUsingLinkedList.push(10);
        assertEquals(stackUsingLinkedList.size(), 1);

        //Clear stack and check size now 0
        stackUsingLinkedList.clear();
        assertEquals(stackUsingLinkedList.size(), 0);

        //Try and clear empty stack, check is empty
        stackUsingLinkedList.push(10);
        stackUsingLinkedList.pop();
        stackUsingLinkedList.clear();
        assertEquals(stackUsingLinkedList.size(), 0);

        //Fill the stack and check its full
        for (int x = 9; x >= 0; x--) {
            stackUsingLinkedList.push(x);
        }
        assertEquals(stackUsingLinkedList.size(), 10);
        //Clear the stack and check its empty
        stackUsingLinkedList.clear();
        assertEquals(stackUsingLinkedList.size(), 0);
    }

}
