package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private LinkedList<T> stack;

  public Stack() {
    stack = new LinkedList<T>();
  }

  public void push(T data) {
    stack.insert(0, data);
  }

  public T pop() {
    return stack.remove(0);
  }

  public T peek() {
    return stack.get(0);
  }

  public int size() {
    return stack.size();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public String toString() {
    return stack.toString();
  }
}
