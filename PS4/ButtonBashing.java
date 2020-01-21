import java.util.*;

public class ButtonBashing {

  public static class Node {
    public int time;
    public int count;
    public Node(int time, int count) {
      this.time = time;
      this.count = count;
    }
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int tc = io.getInt(); // testcases
    for (int i = 0; i < tc; i++) {
      // input
      int n = io.getInt(); // no. of buttons, aka number of possible edges to add to current node each time
      int t = io.getInt(); // want to find this value

      int[] countAtTime = new int[3601]; // 0 to 3600 seconds. Value of 0 if unvisited. Stores how many counts has elapsed including the one for Node@currentIndex. All initialised automatically at 0.
      ArrayList<Integer> edgeWeights = new ArrayList<>(); // these are the outoging edges weights from every Node later on, as we have a choice on progression for all these edges
      for (int j = 0; j < n; j++) {
        edgeWeights.add(io.getInt());
      }

      // compute: to update the countAtTime array
      LinkedList<Node> q = new LinkedList<>();
      Node current = new Node(0, 0); // dummy Node to get ball rolling
      q.add(current);
      while (!q.isEmpty()){
        current = q.poll();

        if (countAtTime[current.time] != 0 && current.time != 3600) {continue;} // visited before so no point revisiting
        countAtTime[current.time] = current.count; // else, mark as visited, being != 0 now

        // Do the check
        if (current.time == t){
          break; // time can be exactly found!
        } else {
          // time not found: add neighbours to q only if never visited neighbour before
          for (Integer edge: edgeWeights){
            int timePosition = (current.time + edge > 3600) ? 3600 : current.time + edge; // Question says that if >3600, we take it as 3600 on the dot.
            if (timePosition >= 0 && countAtTime[timePosition] == 0) { // we cannot have negative time. In either way, if time asked is 0, we simply do a trivial solution setting "0 0" output as we don't need to do anything. This is done later on
              // neighbour must contain a time that is valid, ie 0 to 3600 incl
              Node neighbour = new Node(timePosition, current.count+1); // each new neighbour presents the next 'phase' of progression, so they have a count +1 from current
              q.add(neighbour);
            }
          }
        }
      }

      // output: at this stage, countAtTime should be fully updated. We only check the results and do output.
      int nextCeilIndex = t; // this is set to be the next time that can be obtained
      while (countAtTime[nextCeilIndex] == 0 && nextCeilIndex < 3600) {
        nextCeilIndex++; // node, this is the timeSoFar, which >= t
      }
      int leftoverTime = (t <= 0) ? 0 : (nextCeilIndex > 3600) ? 3600 : nextCeilIndex - t; // <= 0: trivial. > 3600: absurd (only obtainable if question gives this ridiculous number), set to 3600 as per qn. Else valid range.
      io.println("" + countAtTime[nextCeilIndex] + " " + leftoverTime); // countAtTime gives closes present time obtainable. leftoverTime gives the remaining time from this time, or one of the trivial cases.
      io.flush();
    }
  }
}
