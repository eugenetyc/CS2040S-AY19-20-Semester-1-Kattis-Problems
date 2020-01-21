import java.util.*;

public class Arbitrage {

  public static class Node {
    public String id;
    public double value = 0; // to change and compare
    public ArrayList<Edge> neighbours = new ArrayList<>();
    public Node (String id) {
      this.id = id;
    }
  }

  public static class Edge {
    public String src;
    public String dest;
    public double w; // remains the same, but used to change Node values
    public Edge(String src, String dest, double w) {
      this.src = src;
      this.dest = dest;
      this.w = w;
    }
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    while (true) {

      // input

      int n = io.getInt();
      if (n == 0) {
        break;
      }
      // setup Nodes in a HashMap
      HashMap<String, Node> nodes = new HashMap<>(); // for quick-access nodes
      for (int i = 0; i < n; i++) {String id = io.getWord(); Node x = new Node(id); nodes.put(id, x);}
      // setup Edges in a List. Edges never change. Only Nodes change.
      int edgesTotal = io.getInt();
      ArrayList<Edge> edges = new ArrayList<>(); // for storing edges
      for (int i = 0; i < edgesTotal; i++) {
        String src = io.getWord(); String dest = io.getWord(); String rate = io.getWord(); String[] ratio = rate.split(":"); double num = Double.parseDouble(ratio[0]); double denom = Double.parseDouble(ratio[1]); // helper variables
        Edge e = new Edge(src, dest, -Math.log(num/denom)); // make edge
        edges.add(e); // update edges
        nodes.get(src).neighbours.add(e); // update nodes' neighbours
      }

      // compute: Bellman-Ford SSSP Algo with -log(weight) edges
      if (edges.isEmpty()) {
        io.println("Ok"); // if there aren't even any edges, this case is trivial
        io.flush();
        continue;
      }
      Edge startEdge = edges.get(0); // otherwise, if there exists some edges, bellmanford will give the shortest path available after n-1 cycles of E iterations of relaxation, unless there are negative cycles
      nodes.get(startEdge.src).value = 0;
      for (int i = 0; i < n - 1; i++) {
        for (Edge e: edges) {
          // n-1 cycles of E iterations of relaxation
          Node startNode = nodes.get(e.src); Node destNode = nodes.get(e.dest); double weight = e.w;
          if (startNode.value + weight > destNode.value) {
            // relaxation is now modified. We want to find the smallest value ratio.
            // But, a smaller exchange rate ratio results in a larger positive value (ie the -log(num/denom) value)
            // so, since we are adding -log(num/denom) values, we want the shortest path
            // and to do so, need to take larger values than current destination's stored value whenever possible.
            // in essense, we find the "largest path" as it represents the "actual smallest path"
            destNode.value = startNode.value + weight;
          }
        }
      }
      // Now given that we have all the "actual smallest paths" represented by the "largest paths" (the larger values seen),
      // we check for any larger values for the current edge which should not be happening unless there is a negative cycle
      // because negative cycles imply an always existing cycle which will function to always increase values

      // layman terms: negative cycle -> keeps giving shorter paths because we get more negative values.
      // layman terms: shorter paths are represented by larger -log(x) values (inclusive of the -ve sign)
      boolean arbitrage = false;
      for (Edge e: edges) {
        Node startNode = nodes.get(e.src); Node destNode = nodes.get(e.dest); double weight = e.w;
        if (startNode.value + weight > destNode.value) {
          arbitrage = true;
          break;
        }
      }
      // output
      if (arbitrage) {
        io.println("Arbitrage");
        io.flush();
      } else {
        io.println("Ok");
        io.flush();
      }
    }
  }
}
