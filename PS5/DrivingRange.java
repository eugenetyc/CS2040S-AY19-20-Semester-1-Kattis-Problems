import java.util.*;

public class DrivingRange {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    // validity checking and setup
    int n = io.getInt(); int m = io.getInt();
    if (m == 0) {io.println("IMPOSSIBLE"); io.flush(); return;}

    ArrayList<Edge> mstEdges = new ArrayList<Edge>(); // result
    UnionFindDisjointSet ufds = new UnionFindDisjointSet(n); // initial trees of single node
    ArrayList<Edge> allEdges = new ArrayList<Edge>(); // store all edges to process

    // store all the edges and then sort them in ascending weight
    for (int i = 0; i < m; i++) {
      int u = io.getInt(); int v = io.getInt(); int w = io.getInt();
      if (u > v) {
        int temp = u;
        u = v;
        v = temp;
      }
      allEdges.add(new Edge(u, v, w));
    }
    Collections.sort(allEdges, new weightComparator());

    // processing and update MST
    int mstMaxWeight = 0;
    for (int i = 0; i < m; i++) {
      Edge e = allEdges.get(i); int u = e.src; int v = e.dest;
      if (!ufds.isSameSet(u, v)) {
        mstEdges.add(e); // added according to weight increasing
        ufds.union(u, v); // join them into same set
        mstMaxWeight = (e.weight > mstMaxWeight) ? e.weight : mstMaxWeight; // accumulate total weight so far
      }
    }

    // output
    if (!ufds.isSpanning()) {
      io.println("IMPOSSIBLE");
      io.flush();
    } else {
      io.println(mstMaxWeight); // print total weight
      io.flush();
    }
  }
  public static class UnionFindDisjointSet {

      public int[] parent;
      public int[] size;
      public int temp, rootU, rootV;
      public boolean[] visited; // to check if spanning or not: visited indicates it is already in the MST so far

      public UnionFindDisjointSet(int numVertices) {
          parent = new int[numVertices]; // begins from vertex 0, to numVertices-1.
          size = new int[numVertices];
          visited = new boolean[numVertices];
          // this is a constructor to store all nodes and vertices of the MST here!
          for (int n = 0; n < numVertices; n++) {
            parent[n] = n;
            size[n] = 1;
            visited[n] = false;
          }
      }

      public void union(int u, int v) {
        visited[u] = true;
        visited[v] = true;
        rootU = u;
        while (parent[rootU] != rootU) {
          rootU = parent[rootU];
        }
        while (parent[u] != u) {
          temp = parent[u]; // pause traversal
          parent[u] = rootU; // set parent to be root
          u = temp; // continue traversal
        }
        rootV = v;
        while (parent[rootV] != rootV) {
          rootV = parent[rootV];
        }
        while (parent[v] != v) {
          // set all traversed nodes to have parent == root
          temp = parent[v]; // pause traversal
          parent[v] = rootV; // set parent to be root
          v = temp; // continue traversal
        }
        if (size[rootU] >= size[rootV]) {
          // u's tree is larger, should be the ultimate root
          parent[rootV] = rootU;
          size[rootU] += size[rootV];
        } else {
          // v's tree is larger, should be ultimate root
          parent[rootU] = rootV;
          size[rootV] += size[rootU];
        }
      }

      public boolean isSameSet(int u, int v) {

          // 1. identify true rootU, do not need path compression
          rootU = u;
          while (parent[rootU] != rootU) {
            // root's parent will be itself
            rootU = parent[rootU];
          }

          // 2. identify true rootV, do not need path compression
          rootV = v;
          while (parent[rootV] != rootV) {
            // root's parent will be itself
            rootV = parent[rootV];
          }

          return (rootU == rootV);
      }

      public boolean isSpanning() {
        boolean spans = true;
        for (int i = 0; i < visited.length; i++) {
          spans = spans && visited[i];
        }
        return spans;
      }
  }
  public static class Edge {
    public int src;
    public int dest;
    public int weight;
    public Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
  }
  public static class weightComparator implements Comparator<Edge> {
    public int compare(Edge e1, Edge e2) {
      return e1.weight - e2.weight;
    }
  } // based on weight

/*
  public static class printedEdgeComparator implements Comparator<Edge> {
    public int compare(Edge e1, Edge e2) {
      if (e1.src != e2.src) {
        return e1.src - e2.src;
      } else {
        // e1.src == e2.src, look at dest. Smaller dest appears first.
        return e1.dest - e2.dest;
      }
    }
  } // based on same line  // based on inter-line lexi. order
*/
}
