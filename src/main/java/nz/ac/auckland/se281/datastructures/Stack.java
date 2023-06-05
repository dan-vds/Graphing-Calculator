package nz.ac.auckland.se281.datastructures;

/**
 * A class representing a stack data structure.
 *
 * @param <T> the type of data stored in the stack.
 */
public class Stack<T> {
  private LinkedList<T> stack;

  /** Constructor for Stack, initialises stack as a new LinkedList. */
  public Stack() {
    // initialise stack as a new LinkedList
    stack = new LinkedList<T>();
  }

  /**
   * Adds a new node to the top of the stack.
   *
   * @param data the data to be stored in the new node.
   */
  public void push(T data) {
    // add the new node to the top of the stack
    stack.addFirst(data);
  }

  /**
   * Removes the top node from the stack.
   *
   * @return the data stored in the node being removed.
   */
  public T pop() {
    // remove the top node from the stack
    return stack.removeFirst();
  }

  /**
   * Returns the top node in the stack.
   *
   * @return the data stored in the top node.
   */
  public T peek() {
    // return the top node in the stack
    return stack.getFirst();
  }

  /**
   * Computes the size of the stack.
   *
   * @return the size of the stack.
   */
  public int size() {
    // return the size of the stack
    return stack.size();
  }

  /**
   * Checks if the stack is empty.
   *
   * @return true if the stack is empty, false otherwise.
   */
  public boolean isEmpty() {
    // return true if the stack is empty, false otherwise
    return stack.isEmpty();
  }

  @Override
  public String toString() {
    // return the string representation of the stack
    return stack.toString();
  }
}
