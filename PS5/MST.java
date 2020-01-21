import java.util.*;

public class MST {
  public static void main(String[] args) {
    // for quick-access
    Kattio io = new Kattio(System.in, System.out);
    int n, m, u, v, w, temp, src, dest, weight;

    // for processing
    PriorityQueue<Edge> edgesToProcess; // store Edges in increasing weight
    boolean[] visited;
    ArrayList<ArrayList<Edge>> graph; // first access node via index, then access neighbour via 2nd index

    // for result
    ArrayList<Edge> mstEdges; // store output results!

    while (true) {
      // validity checking
      n = io.getInt(); m = io.getInt();
                                                                                        //System.out.println("n = " + n + " and m = " + m);
      if (n == 0 && m == 0) {break;}

      // valid initialisation: NEED TO RESET EVERYTIME!
      edgesToProcess = new PriorityQueue<>();
      visited = new boolean[n];
      graph = new ArrayList<>();
      mstEdges = new ArrayList<>();

      // initialise nodes
      for (int i = 0; i < n; i++) {
        visited[i] = false; // unvisited
        graph.add(new ArrayList<Edge>());
      }
      // storing edges
      for (int i = 0; i < m; i++) {
        u = io.getInt(); v = io.getInt(); w = io.getInt();
        if (v < u) {temp = u; u = v; v = temp;} // always want u to be smaller, v to be larger
        graph.get(u).add(new Edge(u, v, w)); // always added with smaller number -> larger number inside each graph
      }

      // start the processing
      edgesToProcess.add(new Edge(0, 0, 0)); // dummy edge to get things started
      visited[0] = true; // visited
      Edge first = edgesToProcess.poll();
      mstEdges.add(first); // added to MST
      edgesToProcess.addAll(graph.get(first.dest)); // Simply add all its Edges
      while(!edgesToProcess.isEmpty()) {
        Edge currentEdge = edgesToProcess.poll();
        src = currentEdge.src; dest = currentEdge.dest; weight = currentEdge.weight;
        System.out.println("Edge with u = " + src + " and v = " + dest + " with weight = " + weight + "has destination v visited = " + visited[dest]);

        // Main Goal: Add all smallest edges for unvisited nodes.
        // Case 1 - visited: do nothing.
        // Case 2 - unvisited: visit, add to MST both the dest and edge, add their edges to edgesToProcess or update them
        // Note: Updating: if it is already inside, then comparator will enable the smaller value to appear at the top before larger value
        // -> Updating via lazy deletion. In either case inside or not, we just simply add.
        if (!visited[dest] && visited[src]) {
          visited[dest] = true; // visited
          mstEdges.add(currentEdge); // added to MST
          edgesToProcess.addAll(graph.get(dest)); // Simply add all its Edges
        }
      }

      // post-processing
      // if any are unvisited, clearly it is not a spanning tree, let alone MST.

      // MST check
      boolean mstAvailable = true;
      for (int i = 0; i < n; i++) {
                                                                                            //System.out.println("visited[" + i + "] is " + visited[i]);
        mstAvailable = mstAvailable && visited[i];
      }

      // Output
      if (!mstAvailable) {
        // MST not available
        io.println("Impossible");
        io.flush();
      } else {
        // MST available
        int mstWeight = 0;
        for (int i = 0; i < mstEdges.size(); i++) {
          mstWeight += mstEdges.get(i).weight;
                                                                                          // System.out.println("mstEdges[" + i + "] has source " + mstEdges.get(i).src + " and dest = " + mstEdges.get(i).dest + " and weight = " + mstEdges.get(i).weight);
        }
        io.println(mstWeight); // print the total weight of MST
        io.flush();
        //Collections.sort(mstEdges, new printedEdgeComparator2());
        //Collections.sort(mstEdges, new printedEdgeComparator());
        for (int i = 1; i < mstEdges.size(); i++) {
          // i starts from 1 instead of 0 because we exclude the dummy 0-0 edge of weight 0
          // we now want the 2 specifications:
          // 1: Within each pair, smaller number before larger in Edge. Trivial based on above.
          // 2: Between pairs, smaller source numbers will print first, then same source smaller dest, and so on
          Edge e = mstEdges.get(i);
          io.println(e.src + " " + e.dest);
          io.flush();
        }
      }
    }
  }

  public static class Edge implements Comparable<Edge>{
    public int src;
    public int dest;
    public int weight;
    public Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
    public int compareTo(Edge other) {
      // edges with smaller weight are higher prioritised
      return this.weight - other.weight;
    }
  }

  public static class printedEdgeComparator implements Comparator<Edge> {
    public int compare(Edge e1, Edge e2) {
      if (e1.src != e2.src) {
        return e1.src - e2.src;
      } else {
        // e1.src == e2.src, look at dest. Smaller dest appears first.
        return e1.dest - e2.dest;
      }
    }
  }

  /*public static class printedEdgeComparator2 implements Comparator<Edge> {
    public int compare(Edge e1, Edge e2) {
      return e1.weight - e2.weight;
    }
  }*/
}
