import java.util.*;

public class NTNU{
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int T = io.getInt(); // # test cases
    for (int i = 0; i < T; i++) {
      int C = io.getInt(); // #campuses; bus routes = C*(C-1)/2
      int L = io.getInt(); // #lectures

      // 1. Settle all shortest travel times first
      // Get all paths shortest paths for shortest bus travelling times from A->B : Floyd-Warshall
      // Collect all bus edges first
      int[][] busEdges = new int[C][C];
      for (int v = 0; v < C; v++) {
        for (int w = 0; w < C; w++) {
          busEdges[v][w] = (v == w) ? 0 : Integer.MAX_VALUE;
        }
      }
      for (int z = 0; z < (C*(C-1)/2); z++) {
        int x = io.getInt(); int y = io.getInt(); int w = io.getInt();
        busEdges[x][y] = w;
        busEdges[y][x] = w;
      }
      //memoization table S has |C| rows and |C| columns: technically same as busEdges, but I just want to make them separate
      int[][] apsp = new int[C][C];
      // Initialize every pair of nodes, those unspecified will be 0
      for (int v = 0; v < C; v++) {
        for (int w = 0; w < C; w++) {
          apsp[v][w] = busEdges[v][w];
        }
      }
      // For sets P0, P1, P2, P3, …, for every pair (v,w)
      for (int k = 0; k < C; k++) {
        for (int v = 0; v < C; v++) {
          for (int w = 0; w < C; w++) {
            int candidate = apsp[v][k] + apsp[k][w];
            apsp[v][w] = (apsp[v][w] < candidate) ? apsp[v][w] : candidate;
          }
        }
      }

      // Post-Step1: We now have the shortest travelling times from A to B

      // 2. Sorting and collecting all Lectures as Edges
      // We make use of the 2 arrays: lectureEdges (all lectures), attendance (stores how many lectures attended before and including the current index's lecture)
      // Collect all lecture edges first
      Edge[] lectureEdges = new Edge[L];
      for (int z = 0; z < L; z++) {
        lectureEdges[z] = new Edge(io.getInt(), io.getInt(), io.getInt());
      }
      // Arrange all lectures with earliest having smallest index.
      Arrays.sort(lectureEdges);

      // 3. Considering which lectures to attend, and maximising them
      // Consider every Campus as a possible start point
      // We begin the first lecture at the respective lectures' location,
      // saving the intial time to travel, so we initialise all to be 1 lecture attended so far
      int[] attendance = new int[L];
      Arrays.fill(attendance, 1);
      // Dynamic Programming: We attempt to build on past overlapping subsolutions to get the current solution
      // This means we have to set a limit on how many lectures we consider to attend, increasing it each time
      // We always consider moving from lecture locations v to u.
      for (int v = L-1; v >= 0; v--) { // Working backwards, skip all lectures before this. Obviously, we attend this lecture because we begin from it. Hence all initialised to 1.
        Edge current = lectureEdges[v]; // currently just finished this lecture which starts after lecture v and now consider the next chronological lecture
        for (int u = v+1; u < L; u++) {
          // Note: u > v all the time, so u always starts later than v
          // u always begins from v+1, then v+2, ..., L-1 for a fixed value of v each internal iteration
          Edge next = lectureEdges[u]; // we want to get to this next lecture u chronologically after lecture v, but don't know if we can make it in time
          int travelTimeLimit = next.start - current.end;
          int actualTravelTime = apsp[current.location][next.location];
          // If we can make it in time for the next lecture punctually, then we can attend it -> producing a maximum lectures chain so far.
          // If we can't make it in time, then we don't attend it -> leads to smaller values stored (since we don't modify(increase) the current lecture's stored value)
          // which potentially produces current+1 values less than the supposed later on if this lower valued lecture is accessible from an earlier one (smaller value of v working backwards)
          /// Hence we need an additional condition below for the attendance at current VS attendance at next
          if (actualTravelTime <= travelTimeLimit) {
            if (attendance[v] < attendance[u] + 1) {
              // considering the chain of lecture attendances after this, we want to maximise it, so, we only take the best pick === highest value, and not reset it upon encountered a lower value
              // Hence, since we have considered those behind, and updated their values at these higher indices, we now consider which chain to use after the current one which is 1 index before these.
              // Store the best lecture attendance chain's value+1 to link up current lecture with the most successful chain of lecture attendances.
              // Note it will only increase by at most one since we consider this ONE earlier lecture attended at ONE campus.
              attendance[v] = attendance[u] + 1;
            }
          }
        }
      }
      // We now have the most successful chain's value stored in one of the attendance[x] values!

      // 4. Finally, output the maximum number of lectures attended
      // Now, attendance stores all the maximum values of lectures attended when we last attended a lecture with its rank represented by its index.
      int maximumAttendance = 1;
      for (int z = 0; z < L; z++) {
        maximumAttendance = (attendance[z] > maximumAttendance) ? attendance[z] : maximumAttendance;
      }

      io.println(maximumAttendance);
    }
    io.flush();
  }

  public static class Edge implements Comparable<Edge> {
    public int location;
    public int start;
    public int end;
    public Edge(int location, int start, int end) {
      this.location = location;
      this.start = start;
      this.end = end;
    }
    public int compareTo(Edge other) {
      return this.start - other.start;
    }
  }
}
