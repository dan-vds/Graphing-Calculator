package nz.ac.auckland.se281.datastructures;

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
    throw new UnsupportedOperationException();
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
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
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
