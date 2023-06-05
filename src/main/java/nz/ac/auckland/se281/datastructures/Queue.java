package nz.ac.auckland.se281.datastructures;

/**
 * A class representing a queue data structure.
 *
 * @param <T> the type of data stored in the queue.
 */
public class Queue<T> {

  private LinkedList<T> queue;

  /** Constructor for Queue, initialises queue as a new LinkedList */
  public Queue() {
    // initialise queue as a new LinkedList.
    queue = new LinkedList<T>();
  }

  /**
   * Adds a new node to the end of the queue.
   *
   * @param data the data to be stored in the new node.
   */
  public void enqueue(T data) {
    // add the new node to the end of the queue
    queue.add(data);
  }

  /**
   * Removes the first node from the queue.
   *
   * @return the data stored in the node being removed.
   */
  public T dequeue() {
    // remove the first node from the queue
    return queue.removeFirst();
  }

  /**
   * Returns the first node in the queue.
   *
   * @return the data stored in the first node.
   */
  public T peek() {
    // return the first node in the queue
    return queue.getFirst();
  }

  /**
   * Computes whether the queue is empty or not.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    // return
    return queue.isEmpty();
  }

  /**
   * Overrides the string representation of the queue.
   *
   * @return the string representation of the queue.
   */
  @Override
  public String toString() {
    return queue.toString();
  }
}
