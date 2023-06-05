package nz.ac.auckland.se281.datastructures;

public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;

  /** Constructor for LinkedList, initialises head and tail as null */
  public LinkedList() {
    head = null;
    tail = null;
  }

  /**
   * Adds a new node to the end of the linked list
   *
   * @param data the data to be stored in the new node
   */
  public void add(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setPrev(tail);
      tail.setNext(newNode);
      tail = newNode;
    }
  }

  /**
   * Adds a new node to the start of the linked list
   *
   * @param data the data to be stored in the new node
   */
  public void addFirst(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    }
  }

  /**
   * Returns the first node in the linked list
   *
   * @return the data stored in the first node
   */
  public T getFirst() {
    return head.getData();
  }

  /**
   * Computes the size of the LinkedList
   *
   * @return the size of the LinkedList
   */
  public int size() {
    Node<T> n = head;
    int count = 0;
    while (n != tail) {
      count++;
      n = n.getNext();
    }
    count++;
    return count;
  }

  /**
   * Checks if the LinkedList is empty
   *
   * @return true if the LinkedList is empty, false otherwise
   */
  public boolean isEmpty() {
    if (head == null) {
      return true;
    }
    return false;
  }

  /**
   * Removes the first node in the LinkedList
   *
   * @return the data stored in the first node
   */
  public T removeFirst() {
    Node<T> n = head;
    head = n.getNext();
    return n.getData();
  }

  /**
   * Removes the last node in the LinkedList
   *
   * @return the data stored in the last node
   */
  public void addLast(T data) {
    Node<T> newNode = new Node<T>(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setPrev(tail);
      tail.setNext(newNode);
      tail = newNode;
    }
  }

  @Override
  public String toString() {
    String result = "[";
    if (head != null && tail != null) {
      Node<T> n = head;
      while (n != tail) {
        result += n.toString() + ", ";
        n = n.getNext();
      }
      result += n.toString();
    }
    result += "]";
    return result;
  }
}
