package nz.ac.auckland.se281.datastructures;

/**
 * A class representing a node in a linked list.
 *
 * @param <T> the type of data stored in the node.
 */
public class Node<T> {

  // declaring instance variables for data stored, next and previous nodes
  private T data;
  private Node<T> next;
  private Node<T> prev;

  /**
   * Constructor for Node, initialises data and next as null.
   *
   * @param data the data to be stored in the node.
   */
  public Node(T data) {
    // initialise data and next as null
    this.data = data;
    next = null;
  }

  /**
   * Gets the data stored in the node.
   *
   * @return the data stored in the node.
   */
  public T getData() {
    // return the data stored in the node
    return data;
  }

  /**
   * Sets the data stored in the node.
   *
   * @param data the data to be stored in the node.
   */
  public void setData(T data) {
    // set the data stored in the node
    this.data = data;
  }

  /**
   * Gets the next node from the current node.
   *
   * @return the next node.
   */
  public Node<T> getNext() {
    // return the next node
    return next;
  }

  /**
   * Sets the next node for the current node.
   *
   * @param next the next node.
   */
  public void setNext(Node<T> next) {
    // set the next node
    this.next = next;
  }

  /**
   * Gets the previous node from the current node.
   *
   * @return the previous node.
   */
  public Node<T> getPrev() {
    // return the previous node
    return prev;
  }

  /**
   * Sets the previous node of the current node.
   *
   * @param prev the previous node.
   */
  public void setPrev(Node<T> prev) {
    // set the previous node
    this.prev = prev;
  }

  /**
   * Overrides the string representation of the node.
   *
   * @return the string representation of the node.
   */
  @Override
  public String toString() {
    // return the string representation of the node
    return data.toString();
  }
}
