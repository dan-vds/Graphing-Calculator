package nz.ac.auckland.se281.datastructures;

/**
 * A class representing a linked list data structure.
 *
 * @param <T> the type of data stored in the linked list.
 */
public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;

  /** Constructor for LinkedList, initialises head and tail as null. */
  public LinkedList() {
    // initial head and tail set to null
    head = null;
    tail = null;
  }

  /**
   * Adds a new node to the end of the linked list.
   *
   * @param data the data to be stored in the new node.
   */
  public void add(T data) {
    // create a new node
    Node<T> newNode = new Node<>(data);
    // if the list is empty, set head and tail to the new node
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      // otherwise, set the new node as the next node of the tail
      newNode.setPrev(tail);
      tail.setNext(newNode);
      tail = newNode;
    }
  }

  /**
   * Adds a new node to the start of the linked list.
   *
   * @param data the data to be stored in the new node.
   */
  public void addFirst(T data) {
    // create a new node
    Node<T> newNode = new Node<>(data);
    // if the list is empty, set head and tail to the new node
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      // otherwise, set the new node as the next node of the tail
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    }
  }

  /**
   * Returns the first node in the linked list.
   *
   * @return the data stored in the first node.
   */
  public T getFirst() {
    // return the data from the first node
    return head.getData();
  }

  /**
   * Computes the size of the LinkedList.
   *
   * @return the size of the LinkedList.
   */
  public int size() {
    Node<T> n = head;
    int count = 0;
    // iterate through the list, counting the number of nodes
    while (n != tail) {
      count++;
      n = n.getNext();
    }
    count++;
    // return the number of nodes
    return count;
  }

  /**
   * Checks if the LinkedList is empty.
   *
   * @return true if the LinkedList is empty, false otherwise.
   */
  public boolean isEmpty() {
    // if the head is null, the list is empty
    if (head == null) {
      return true;
    }
    // otherwise, the list is not empty
    return false;
  }

  /**
   * Removes the first node in the LinkedList.
   *
   * @return the data stored in the first node.
   */
  public T removeFirst() {
    Node<T> n = head;
    // remove the current head from the list
    head = n.getNext();
    return n.getData();
  }

  /**
   * Converts the LinkedList to a String.
   *
   * @return the LinkedList as a String.
   */
  @Override
  public String toString() {
    // create a StringBuilder to build the String representation of the list
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    if (head != null && tail != null) {
      Node<T> n = head;
      // iterate through the list, appending each node to the StringBuilder
      while (n != tail) {
        sb.append(n.toString()).append(", ");
        n = n.getNext();
      }
      sb.append(n.toString());
    }

    // close the StringBuilder and return the resulting String
    sb.append("]");
    return sb.toString();
  }
}
