package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private LinkedList<T> stack;

  public Stack() {
    stack = new LinkedList<T>();
  }

  public void push(T data) {
    stack.add(data);
  }

  public T pop() {
    return stack.removeFirst();
  }

  public T peek() {
    return stack.getFirst();
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
