package uk.ac.standrews.cs.cs2001.w08.extention;

/**
 * Node class.
 */
public class Node {

    /**
     * Data node stores.
     */
    public Object node;
    /**
     * Pointer to next node.
     */
    public Node next;

    /**
     * Empty constructor.
     */
    public Node() {
    }

    /**
     * Constructor that takes in data.
     *
     * @param node data for the node.
     */
    public Node(Object node) {
        this.node = node;
    }
}
