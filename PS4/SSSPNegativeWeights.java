import java.util.*;

public class SSSPNegativeWeights {

  public static class Edge {
    // We now need to track the start of each Edge as Edges can be processed from any node depending.
    public int u; // source
    public int v; // destination
    public int w; // weight
    public Edge(int u, int v, int w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    while (true) {
      int n = io.getInt(); int m = io.getInt(); int q = io.getInt(); int s = io.getInt();
      if (n == 0 && m == 0 && q == 0 && s == 0) {
        break;
      }
      ArrayList<Edge> adjList = new ArrayList<>(); // Graph represented by EdgeList
      for (int i = 0; i < m; i++) {
        adjList.add(new Edge(io.getInt(), io.getInt(), io.getInt()));
      }
      int[] dist = new int[n];
      for (int i = 0; i < n; i++) {
        dist[i] = Integer.MAX_VALUE;
      }

      // COMPUTE: Bellman-Ford Algorithm

      // set source distance to 0 and begin Bellman-Ford Algorithm
      dist[s] = 0;
      for (int i = 0; i < n - 1; i++) {
        // running n-1 times gurantees the shortest paths tree
        for (Edge e: adjList) {
          int src = e.u;
          // if dist[src] is Inf, it is either disconnected or not yet updated.
          // We only use its value once it is updated
          if (dist[src] == Integer.MAX_VALUE) {
            continue;
          }
          int dest = e.v;
          int weight = e.w;
          int candidate = dist[src] + weight;
          // if dist[dest] is Inf, then anything other than infinity will become the new distance
          // if dist[dest] is some value, any smaller value will become the new distance
          if (candidate < dist[dest]) {
              dist[dest] = candidate;
          }
        }
      }
      for (int i = 0; i < n - 1; i++){
        // run the nth iteration
        // However, we need to do this check n-1 times.

        for (Edge e: adjList) {
          int src = e.u;
          // if dist[src] is Inf, it is either disconnected and never updated.
          // => if dist[dest] is Inf, then it will still be Inf
          if (dist[src] == Integer.MAX_VALUE) {
            continue;
          }
          int dest = e.v;
          // if dist[src] is -Inf, it is in negative cycle, then clearly dest is in negative cycle
          // => indicate dest is in negative cycle using -Inf
          if (dist[src] == Integer.MIN_VALUE) {
            dist[dest] = Integer.MIN_VALUE;
            continue;
          }
          int weight = e.w;
          int candidate = dist[src] + weight;
          // Now, dist[src] cannot be Inf or -Inf. It must be some value. Any smaller value (dist[src] + weight) becomes the new dist[dest]
          // Any smaller value can only be obtained in nth round if source were in a negative cycle. dest would hence also be in negative cycle.
          // => Set dest to -Inf if the value is supposed to be updated in the nth round.
          if (candidate < dist[dest]) {
            dist[dest] = Integer.MIN_VALUE;
          }
        }
    }

      // OUTPUT
      // Lastly, serve all queries
      for (int i = 0; i < q; i++) {
        int query = io.getInt();
        if (dist[query] == Integer.MAX_VALUE) {
          // if it is not connected, it will not be updated ie stays at Int Max
          io.println("Impossible"); io.flush();
        } else if (dist[query] == Integer.MIN_VALUE) {
          // Part of negative cycle
          io.println("-Infinity"); io.flush();
        } else {
          // if it is reacheable
          io.println(dist[query]); io.flush();
  		  }
      }
      io.println(); io.flush();
    }
  }
}
