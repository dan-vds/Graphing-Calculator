package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  public Set<T> getRoots() {
    Set<T> roots = new HashSet<T>();
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
      Set<Set<T>> eqSet = new HashSet<>();
      for (T vertex : verticies) {
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
    return roots;
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
    Set<T> eqClass = new HashSet<>();
    if (!isEquivalence()) {
      return eqClass;
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

    return eqClass;
  }

  public List<T> iterativeBreadthFirstSearch() {
    List<T> visitedNodes = new ArrayList<>();
    List<T> recentlyVisitedNodes = new ArrayList<>();
    Queue<T> queue = new Queue<>();
    Set<T> roots = getRoots();
    for (T vertex : roots) {
      if (!visitedNodes.contains(vertex)) {
        queue.enqueue(vertex);
        visitedNodes.add(vertex);
        while (!queue.isEmpty()) {
          T current = queue.dequeue();
          if (!recentlyVisitedNodes.contains(current)) {
            recentlyVisitedNodes.add(current);
          }
          for (Edge<T> edge : edges) {
            if (edge.getSource().equals(current)
                && !recentlyVisitedNodes.contains(edge.getDestination())) {
              queue.enqueue(edge.getDestination());
              recentlyVisitedNodes.add(edge.getDestination());
            }
          }
          Collections.sort(recentlyVisitedNodes);
          for (T node : recentlyVisitedNodes) {
            if (!visitedNodes.contains(node)) {
              visitedNodes.add(node);
            }
          }
        }
      }
    }
    return visitedNodes;
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
