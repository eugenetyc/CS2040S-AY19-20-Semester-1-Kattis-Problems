import java.util.*;

public class SSSPNonNegWeights {

  public static class Edge implements Comparable<Edge> {
    // We do not need to track the start of each Edge as Edges are processed in increasing order of weight w.
    public int v; // destination
    public int w; // weight
    public Edge(int v, int w) {
      this.v = v;
      this.w = w;
    }
    public int compareTo(Edge other) {
       // a lower weight will be processed first, because by Djikstra's Shortest Path Algorithm,
       // given a Node s, we will process the smaller weighted edge first.
       // Subsequent follow-up Edges from the progressed Node will be added.
       // However, we process lowest Edges from a single Node first.
      return this.w - other.w;
    }
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    while (true) {
      // Validity Check
      int n = io.getInt(); int m = io.getInt(); int q = io.getInt(); int s = io.getInt();
      if (n == 0 && m == 0 && q == 0 && s == 0) {
        break;
      }

      // Graph Setup
      ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(); // Graoh
      int[] dist = new int[n]; // output array
      for (int i = 0; i < n; i++) {
        adjList.add(new ArrayList<Edge>());
        dist[i] = Integer.MAX_VALUE;
      }
      for (int i = 0; i < m; i++) {
        adjList.get(io.getInt()).add(new Edge(io.getInt(), io.getInt())); // receive all Edges so they can be used later
      }

      // Get the ball rolling with given Node s
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      pq.add(new Edge(s, 0));
      dist[s] = 0;

      // Domino effect crrying on from Node s
      while (!pq.isEmpty()) {
        // First take out Edge which is actually a self-inserted Edge of s pointing to itself
        // If this is not the first iteration, then it will be the smallest weighted Edge
        Edge x = pq.poll();
        // Find the destination v of this Node, which for the case of s -> s, s itself.
        // Otherwise if not first iteration, it will be destination of the Edge
        int u = x.v;
        for (int i = 0; i < adjList.get(u).size(); i++) {
          // Find all neighbours of s and relax the edges between current Node u and neighbour
          // Note that u is current Node, i is the index running through all its Edges, and .w gives the weight of Edge at i
          // Relaxation: Either take the path  to neighbour directly, to which we assume to be large (infinity) at beginning,
          // or to take path to u then to neighbour
          int neighbour = adjList.get(u).get(i).v;
  				if (dist[neighbour] > dist[u] + adjList.get(u).get(i).w){
  					dist[neighbour] = dist[u] + adjList.get(u).get(i).w;
            // Note that because we now have a possibly empty PQ but want to continue from the neighbour with the lowest weight value,
            // and the PQ works by taking the node with the smallest value accumulated thus far. Hence,
            // we insert an edge with destination of neighbour and weight value of dist[neighbour] to represent the accumulated value thus far
  					pq.add(new Edge(neighbour, dist[neighbour]));
  				}
        }
      }

      // Lastly, serve all queries
      for (int i = 0; i < q; i++){
        int query = io.getInt();
  			if (dist[query] != Integer.MAX_VALUE) {
          // if it is reacheable
          io.println(dist[query]);
          io.flush();
        } else {
          // if it is not reachable, it will not be updated
          io.println("Impossible");
          io.flush();
  		  }
      }
      io.println();
      io.flush();

    }
  }
}
