package uk.ac.standrews.cs.cs2001.w08.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import uk.ac.standrews.cs.cs2001.w08.testExtentions.DoubleStackQueueTests;
import uk.ac.standrews.cs.cs2001.w08.testExtentions.PriorityQueueTests;
import uk.ac.standrews.cs.cs2001.w08.testExtentions.QueueTests;
import uk.ac.standrews.cs.cs2001.w08.testExtentions.StackUsingLinkedListTest;


/**
 * Run all the test classes.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayDoubleStackTests.class, ArrayPriorityQueueTests.class,
        DoubleStackQueueTests.class, PriorityQueueTests.class,
        QueueTests.class, StackUsingLinkedListTest.class})
public class Tests {


}
