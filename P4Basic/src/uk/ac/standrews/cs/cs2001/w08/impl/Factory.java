package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.interfaces.IDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IFactory;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

/**
 * This class implements a singleton factory.
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     *
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Make new DoubleStack object.
     *
     * @param maxSize the maximum size of the stack
     * @return DoubleStack object.
     */
    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        return new DoubleStack(maxSize);
    }

    /**
     * Make new PriorityQueue object.
     *
     * @param maxSize the maximum size of the stack
     * @return PriorityQueue object.
     */
    @Override
    public IPriorityQueue makePriorityQueue(int maxSize) {
        return new PriorityQueue(maxSize);
    }


}
