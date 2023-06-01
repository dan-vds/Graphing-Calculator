package nz.ac.auckland.se281.datastructures;

public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;

  public LinkedList() {
    head = null;
    tail = null;
  }

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

  public T get(int index) {
    Node<T> n = head;
    for (int i = 0; i <= index; i++) {
      if (i == index) {
        return n.getData();
      }
      n = n.getNext();
    }
    return null;
  }

  public void insert(int index, T data) {
    Node<T> n = head;
    for (int i = 0; i <= index; i++) {
      if (i == index - 1) {
        Node<T> newNode = new Node<T>(data);
        newNode.setNext(n.getNext());
        newNode.setPrev(n);
        n.setNext(newNode);
      }
      n = n.getNext();
    }
  }

  public void remove(int index) {
    Node<T> n = head;
    for (int i = 0; i <= index; i++) {
      if (i == index) {
        if (n != tail && n != head) {
        } else if (n == tail) {
          tail = n.getPrev();
        } else if (n == head) {
          head = n.getNext();
        }
      }
      n = n.getNext();
    }
  }

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

  public boolean isEmpty() {
    if (head == null) {
      return true;
    }
    return false;
  }

  public int indexOf(T data) {
    Node<T> n = head;
    int count = 0;
    while (n != tail) {
      if (n.getData() == data) {
        return count;
      }
      count++;
      n = n.getNext();
    }
    if (tail.getData() == data) {
      return count;
    }
    return -1;
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
