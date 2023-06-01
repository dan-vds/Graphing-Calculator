package nz.ac.auckland.se281.datastructures;

public class Queue<T> {

  private LinkedList<T> queue;

  public Queue() {
    queue = new LinkedList<T>();
  }

  public void enqueue(T data) {
    queue.insert(queue.size(), data);
  }

  public T dequeue() {
    return queue.removeFirst();
  }

  public T peek() {
    return queue.getFirst();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}
