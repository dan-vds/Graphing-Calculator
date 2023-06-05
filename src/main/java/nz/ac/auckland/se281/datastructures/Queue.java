package nz.ac.auckland.se281.datastructures;

public class Queue<T> {

  private LinkedList<T> queue;

  /** Constructor for Queue, initialises queue as a new LinkedList */
  public Queue() {
    queue = new LinkedList<T>();
  }

  /**
   * Adds a new node to the end of the queue
   *
   * @param data the data to be stored in the new node
   */
  public void enqueue(T data) {
    queue.addLast(data);
  }

  /**
   * Removes the first node from the queue
   *
   * @return the data stored in the node being removed
   */
  public T dequeue() {
    return queue.removeFirst();
  }

  /**
   * Returns the first node in the queue
   *
   * @return the data stored in the first node
   */
  public T peek() {
    return queue.getFirst();
  }

  /**
   * Computes the size of the queue
   *
   * @return the size of the queue
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}
