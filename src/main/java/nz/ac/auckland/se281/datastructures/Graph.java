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

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
    adjacency = new HashMap<T, ArrayList<T>>();
    for (T vertex : verticies) {
      ArrayList<T> nextVertices = new ArrayList<T>();
      for (Edge<T> edge : edges) {
        if (edge.getSource() == vertex) {
          nextVertices.add(edge.getDestination());
        }
      }
      nextVertices.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
      adjacency.put(vertex, nextVertices);
    }
  }

  public Set<T> getRoots() {
    ArrayList<T> roots = new ArrayList<T>();
    Set<T> finalRoots = new LinkedHashSet<T>();
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
    if (this.isEquivalence()) {
      Set<Set<T>> eqSet = new LinkedHashSet<>();
      // for (T vertex : verticies) {
      //   Set<T> eqClass = getEquivalenceClass(vertex);
      //   if (!eqSet.contains(eqClass)) {
      //     eqSet.add(eqClass);
      //   }
      // }
      // for (Set<T> eqClass : eqSet) {
      //   T root = Collections.min(eqClass);
      //   roots.add(root);
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
    roots.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
    for (T root : roots) {
      finalRoots.add(root);
    }
    return finalRoots;
  }

  public boolean isReflexive() {
    for (T vertex : verticies) {
      Boolean hasLoop = false;
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          hasLoop = true;
        }
      }
      if (!hasLoop) {
        return false;
      }
    }
    return true;
  }

  public boolean isSymmetric() {
    for (Edge<T> edge : edges) {
      Boolean hasReturn = false;
      for (Edge<T> edge2 : edges) {
        if (edge.getSource().equals(edge2.getDestination())
            && edge.getDestination().equals(edge2.getSource())) {
          hasReturn = true;
        }
      }
      if (!hasReturn) {
        return false;
      }
    }
    return true;
  }

  public boolean isTransitive() {
    for (Edge<T> edge : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge.getDestination().equals(edge2.getSource())) {
          Boolean hasTransitive = false;
          for (Edge<T> edge3 : edges) {
            if (edge.getSource().equals(edge3.getSource())
                && edge2.getDestination().equals(edge3.getDestination())) {
              hasTransitive = true;
            }
          }
          if (!hasTransitive) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isAntiSymmetric() {
    for (Edge<T> edge : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge.getSource().equals(edge2.getDestination())
            && edge.getDestination().equals(edge2.getSource())) {
          if (!edge.getSource().equals(edge.getDestination())) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isEquivalence() {
    return isReflexive() && isSymmetric() && isTransitive();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> eqClassSet = new LinkedHashSet<>();
    ArrayList<T> eqClass = new ArrayList<>();
    if (!isEquivalence()) {
      return eqClassSet;
    } else {
      eqClass.add(vertex);
      boolean moreVerticiesToCheck = true;

      while (moreVerticiesToCheck) {
        moreVerticiesToCheck = false;
        for (Edge<T> edge : edges) {
          T source = edge.getSource();
          T destination = edge.getDestination();

          if (eqClass.contains(source) && !eqClass.contains(destination)) {
            eqClass.add(destination);
            moreVerticiesToCheck = true;
          }
        }
      }
    }

    eqClass.sort(Comparator.comparing(vertices2 -> Integer.parseInt(vertices2.toString())));
    for (T vertex2 : eqClass) {
      eqClassSet.add(vertex2);
    }
    return eqClassSet;
  }

  public List<T> iterativeBreadthFirstSearch() {
    List<T> visitedNodes = new ArrayList<>();
    Queue<T> queue = new Queue<T>();
    Set<T> roots = getRoots();

    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        queue.enqueue(vertex);
        visitedNodes.add(vertex);

        while (!queue.isEmpty()) {
          T current = queue.peek();
          queue.dequeue();
          List<T> destinationNodes = adjacency.get(current);
          if (destinationNodes != null) {
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

  public List<T> iterativeDepthFirstSearch() {
    List<T> visitedNodes = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    Set<T> roots = getRoots();

    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        stack.push(vertex);

        while (!stack.isEmpty()) {
          T current = stack.pop();
          if (!visitedNodes.contains(current)) {
            visitedNodes.add(current);
          }
          List<T> destinationNodes = adjacency.get(current);
          if (destinationNodes != null) {
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

    return visitedNodes;
  }

  public List<T> recursiveBreadthFirstSearch() {
    List<T> visitedNodes = new ArrayList<>();
    Set<T> roots = getRoots();
    Queue<T> queue = new Queue<>();

    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        queue.enqueue(vertex);
        visitedNodes.add(vertex);
        recursiveBFS(queue, visitedNodes);
      }
    }

    return visitedNodes;
  }

  private void recursiveBFS(Queue<T> queue, List<T> visitedNodes) {
    if (queue.isEmpty()) {
      return;
    }

    T current = queue.peek();
    queue.dequeue();
    List<T> destinationNodes = adjacency.get(current);

    if (destinationNodes != null) {
      for (T destinationNode : destinationNodes) {
        if (!visitedNodes.contains(destinationNode)) {
          queue.enqueue(destinationNode);
          visitedNodes.add(destinationNode);
        }
      }
    }

    recursiveBFS(queue, visitedNodes);
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
