package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private LinkedList<T> stack;

  /** Constructor for Stack, initialises stack as a new LinkedList */
  public Stack() {
    stack = new LinkedList<T>();
  }

  /**
   * Adds a new node to the top of the stack
   *
   * @param data the data to be stored in the new node
   */
  public void push(T data) {
    stack.addFirst(data);
  }

  /**
   * Removes the top node from the stack
   *
   * @return the data stored in the node being removed
   */
  public T pop() {
    return stack.removeFirst();
  }

  /**
   * Returns the top node in the stack
   *
   * @return the data stored in the top node
   */
  public T peek() {
    return stack.getFirst();
  }

  /**
   * Computes the size of the stack
   *
   * @return the size of the stack
   */
  public int size() {
    return stack.size();
  }

  /**
   * Checks if the stack is empty
   *
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public String toString() {
    return stack.toString();
  }
}
