package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  private Set<T> verticies;
  private Set<Edge<T>> edges;
  private HashMap<T, ArrayList<T>> adjacency;

  /**
   * Constructor for Graph.
   *
   * @param verticies verticies of the graph that has been created.
   * @param edges edges of the graph that has been created.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    // Setting the verticies and edges of the graph
    this.verticies = verticies;
    this.edges = edges;
    // Creating a hashmap of the adjacency list
    adjacency = new HashMap<T, ArrayList<T>>();
    for (T vertex : verticies) {
      // Using an arraylist of the next verticies
      ArrayList<T> nextVertices = new ArrayList<T>();
      for (Edge<T> edge : edges) {
        if (edge.getSource() == vertex) {
          nextVertices.add(edge.getDestination());
        }
      }
      // Sorting the next verticies
      nextVertices.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
      // Putting the vertex and the next verticies into the adjacency list
      adjacency.put(vertex, nextVertices);
    }
  }

  /**
   * Computes the set of roots in a graph.
   *
   * @return the set of root verticies in the graph.
   */
  public Set<T> getRoots() {
    // Creating an arraylist of the roots, and a set of the final roots
    ArrayList<T> roots = new ArrayList<T>();
    Set<T> finalRoots = new LinkedHashSet<T>();
    // This part of the code adds nodes with an in degree of 0 to the roots arraylist
    for (T vertex : verticies) {
      boolean isRoot = true;
      for (Edge<T> edge : edges) {
        if (edge.getDestination().equals(vertex)) {
          isRoot = false;
        }
      }
      if (isRoot == true) {
        roots.add(vertex);
      }
    }
    // This computes if the graph is equivalence, and if it is, it adds the smallest root of each
    // equivalence class to the roots arraylist
    if (this.isEquivalence()) {
      Set<Set<T>> eqSet = new LinkedHashSet<>();
      for (T vertex : adjacency.keySet()) {

        Set<T> eqClass = getEquivalenceClass(vertex);
        if (!eqSet.contains(eqClass)) {
          eqSet.add(eqClass);
        }
      }
      for (Set<T> eqClass : eqSet) {
        T root = Collections.min(eqClass);
        roots.add(root);
      }
    }
    // Sorting the roots arraylist
    roots.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
    // Adding the roots arraylist to the final roots set
    for (T root : roots) {
      finalRoots.add(root);
    }
    // Returning the final roots set
    return finalRoots;
  }

  /**
   * Checks if the graph is a reflexive graph.
   *
   * @return returns true if the graph is reflexive, false otherwise.
   */
  public boolean isReflexive() {
    // Looping through every vertex in the graph
    for (T vertex : verticies) {
      Boolean hasLoop = false;
      for (Edge<T> edge : edges) {
        // Checking if the vertex has a loop
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          hasLoop = true;
        }
      }
      if (!hasLoop) {
        // If any vertex does not have a loop, the graph is not reflexive
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the graph is a symmetric graph.
   *
   * @return returns true if the graph is symmetric, false otherwise.
   */
  public boolean isSymmetric() {
    // Looping through every edge in the graph
    for (Edge<T> edge : edges) {
      Boolean hasReturn = false;
      // Checking if the edge has a return edge
      for (Edge<T> edge2 : edges) {
        if (edge.getSource().equals(edge2.getDestination())
            && edge.getDestination().equals(edge2.getSource())) {
          hasReturn = true;
        }
      }
      if (!hasReturn) {
        // If any edge does not have a return edge, the graph is not symmetric
        return false;
      }
    }
    // If every edge has a return edge, the graph is symmetric
    return true;
  }

  /**
   * Checks if the graph is a transitive graph.
   *
   * @return returns true if the graph is transitive, false otherwise.
   */
  public boolean isTransitive() {
    // Looping through every edge in the graph
    for (Edge<T> edge : edges) {
      // Checking if the edge has a transitive edge
      for (Edge<T> edge2 : edges) {
        if (edge.getDestination().equals(edge2.getSource())) {
          Boolean hasTransitive = false;
          // Checking if the edge has a transitive edge
          for (Edge<T> edge3 : edges) {
            // if every edge has a transitive edge, the graph is transitive
            if (edge.getSource().equals(edge3.getSource())
                && edge2.getDestination().equals(edge3.getDestination())) {
              hasTransitive = true;
            }
          }
          if (!hasTransitive) {
            // If any edge does not have a transitive edge, the graph is not transitive
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if the graph is a anti symmetric graph.
   *
   * @return returns true if the graph is anti symmetric, false otherwise.
   */
  public boolean isAntiSymmetric() {
    // Looping through every edge in the graph
    for (Edge<T> edge : edges) {
      // Checking if the edge has a return edge
      for (Edge<T> edge2 : edges) {
        if (edge.getSource().equals(edge2.getDestination())
            && edge.getDestination().equals(edge2.getSource())) {
          if (!edge.getSource().equals(edge.getDestination())) {
            // If any two edges goes both ways but the destination is not the same as the source,
            // the graph is not anti symmetric
            return false;
          }
        }
      }
    }
    // If every edge has a return edge, the graph is anti symmetric
    return true;
  }

  /**
   * Checks if the graph is a equivalence graph.
   *
   * @return returns true if the graph is equivalence, false otherwise.
   */
  public boolean isEquivalence() {
    return isReflexive() && isSymmetric() && isTransitive();
  }

  /**
   * Gets the equivalence class of a vertex
   *
   * @param vertex the vertex to get the equivalence class of
   * @return the set of the equivalence class of the vertex
   */
  public Set<T> getEquivalenceClass(T vertex) {
    // Creating a set of the equivalence class, and arraylist for individual equivalence classes
    Set<T> eqClassSet = new LinkedHashSet<>();
    ArrayList<T> eqClass = new ArrayList<>();
    if (!isEquivalence()) {
      // If the graph is not equivalence, the equivalence class is just an empty set
      return eqClassSet;
    } else {
      // If the graph is equivalence, the equivalence class is computed
      eqClass.add(vertex);
      boolean moreVerticiesToCheck = true;
      while (moreVerticiesToCheck) {
        moreVerticiesToCheck = false;
        for (Edge<T> edge : edges) {
          T source = edge.getSource();
          T destination = edge.getDestination();
          // If the source is in the equivalence class but the destination is not, the destination
          // is added to the equivalence class
          if (eqClass.contains(source) && !eqClass.contains(destination)) {
            eqClass.add(destination);
            moreVerticiesToCheck = true;
          }
        }
      }
    }

    // Sorting the equivalence class
    eqClass.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
    // Adding the equivalence class to the set of equivalence classes
    for (T vertex2 : eqClass) {
      eqClassSet.add(vertex2);
    }
    return eqClassSet;
  }

  /**
   * Conducts an iterative breadth first search on the graph.
   *
   * @return the list of verticies in the order they were visited.
   */
  public List<T> iterativeBreadthFirstSearch() {
    // Creating a list of the visited nodes, a queue, and a set of the roots
    List<T> visitedNodes = new ArrayList<>();
    Queue<T> queue = new Queue<T>();
    Set<T> roots = getRoots();

    // Looping through every root
    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        // If the root has not been visited, it is added to the queue and the visited nodes list
        queue.enqueue(vertex);
        visitedNodes.add(vertex);
        // Looping through every node in the queue
        while (!queue.isEmpty()) {
          T current = queue.peek();
          queue.dequeue();
          // Looping through every destination node of the current node
          List<T> destinationNodes = adjacency.get(current);
          if (destinationNodes != null) {
            // If the destination node has not been visited, it is added to the queue and the
            // visited nodes list
            for (T destinationNode : destinationNodes) {
              if (!visitedNodes.contains(destinationNode)) {
                visitedNodes.add(destinationNode);
                queue.enqueue(destinationNode);
              }
            }
          }
        }
      }
    }

    return visitedNodes;
  }

  /**
   * Conducts an iterative depth first search on the graph.
   *
   * @return the list of verticies in the order they were visited.
   */
  public List<T> iterativeDepthFirstSearch() {
    // Creating a list of the visited nodes, a stack, and a set of the roots
    List<T> visitedNodes = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    Set<T> roots = getRoots();

    // Looping through every root
    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        // If the root has not been visited, it is added to the stack and the visited nodes list
        stack.push(vertex);

        // Looping through every node in the stack
        while (!stack.isEmpty()) {
          // If the node has not been visited, it is added to the visited nodes list
          T current = stack.pop();
          if (!visitedNodes.contains(current)) {
            visitedNodes.add(current);
          }
          // Looping through every destination node of the current node
          List<T> destinationNodes = adjacency.get(current);
          if (destinationNodes != null) {
            // If the destination node has not been visited, it is added to the stack in reverse
            // order
            for (int i = destinationNodes.size() - 1; i >= 0; i--) {
              T destinationNode = destinationNodes.get(i);
              if (!visitedNodes.contains(destinationNode)) {
                stack.push(destinationNode);
              }
            }
          }
        }
      }
    }
    // Returning the visited nodes list
    return visitedNodes;
  }

  /**
   * Conducts a recursive breadth first search on the graph.
   *
   * @return the list of verticies in the order they were visited.
   */
  public List<T> recursiveBreadthFirstSearch() {
    // Creating a list of the visited nodes, a queue, and a set of the roots
    List<T> visitedNodes = new ArrayList<>();
    Set<T> roots = getRoots();
    Queue<T> queue = new Queue<>();

    // Looping through every root
    for (T vertex : roots) {
      // If the root has not been visited, it is added to the queue and the visited nodes list
      if (!visitedNodes.contains(vertex)) {
        queue.enqueue(vertex);
        visitedNodes.add(vertex);
        // Calling the recursive breadth method
        recursiveBreadth(queue, visitedNodes);
      }
    }
    // Returning the visited nodes list
    return visitedNodes;
  }

  private void recursiveBreadth(Queue<T> queue, List<T> visitedNodes) {
    // If the queue is empty, the method is returned
    if (queue.isEmpty()) {
      return;
    }
    // The current node is removed from the queue
    T current = queue.peek();
    queue.dequeue();
    // If the current node has not been visited, it is added to the visited nodes list
    List<T> destinationNodes = adjacency.get(current);
    // Looping through every destination node of the current node
    if (destinationNodes != null) {
      for (T destinationNode : destinationNodes) {
        // If the destination node has not been visited, it is added to the queue and the visited
        // nodes list
        if (!visitedNodes.contains(destinationNode)) {
          queue.enqueue(destinationNode);
          visitedNodes.add(destinationNode);
        }
      }
    }
    // Calling the recursive breadth method
    recursiveBreadth(queue, visitedNodes);
  }

  /**
   * Conducts a recursive depth first search on the graph.
   *
   * @return the list of verticies in the order they were visited.
   */
  public List<T> recursiveDepthFirstSearch() {
    // Creating a list of the visited nodes, a stack, and a set of the roots
    List<T> visitedNodes = new ArrayList<>();
    Set<T> roots = getRoots();
    Stack<T> stack = new Stack<>();

    // Looping through every root
    for (T vertex : roots) {
      // If the root has not been visited, it is added to the stack and the visited nodes list
      if (!visitedNodes.contains(vertex)) {
        stack.push(vertex);
        // Calling the recursive depth method
        recursiveDepth(stack, visitedNodes);
      }
    }
    // Returning the visited nodes list
    return visitedNodes;
  }

  private void recursiveDepth(Stack<T> stack, List<T> visitedNodes) {
    // If the stack is empty, the method is returned
    if (stack.isEmpty()) {
      return;
    }
    // The current node is removed from the stack
    T current = stack.pop();
    // If the current node has not been visited, it is added to the visited nodes list
    if (!visitedNodes.contains(current)) {
      visitedNodes.add(current);
    }
    // Looping through every destination node of the current node
    List<T> destinationNodes = adjacency.get(current);
    // If the destination node has not been visited, it is added to the stack in reverse order
    if (destinationNodes != null) {
      for (int i = destinationNodes.size() - 1; i >= 0; i--) {
        T destinationNode = destinationNodes.get(i);
        if (!visitedNodes.contains(destinationNode)) {
          stack.push(destinationNode);
        }
      }
    }
    // Calling the recursive depth method
    recursiveDepth(stack, visitedNodes);
  }
}
