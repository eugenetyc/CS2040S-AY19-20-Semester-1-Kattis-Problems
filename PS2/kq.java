import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Collections;

public class kq {
  public static void main(String[] args) {

    Kattio io = new Kattio(System.in, System.out);
    // Find the largest energy quest from the current pool of quest which is smaller or equal to X, if tied, by the largest gold reward,
    // Clear the quest, removing it from current pool. Reduce energy X by E of the quest, and add up the gold reward G earned this session.
    // Repeat steps 1 and 2 with remaining amount of energy, until energy left becomes 0, or if there are no more quests to be cleared with remaining energy.
    int N = io.getInt();
    TreeMap<Integer, PriorityQueue<Integer>> cmdMap = new TreeMap<>();
    // This is a TreeMap of PQs that we add gold values to the PQ.

    for (; N > 0; N--) {
      String command = io.getWord();

      if (command.equals("add")) {
        int E = io.getInt();
        int G = io.getInt();
        if (cmdMap.containsKey(E)) {
          // need to add to existing PQ
          cmdMap.get(E).add(G);
        } else {
          // create a new entry consisting of a PQ
          PriorityQueue<Integer> p = new PriorityQueue<>(Collections.reverseOrder());
          p.add(G);
          cmdMap.put(E, p);
        }

      } else {
        // query X

        long totalGoldEarned = 0;
        int X = io.getInt();
        PriorityQueue<Integer> current;
        Integer currentKey;

        while (X >= 0) {
          // Get the highest energy doable mission available
          currentKey = cmdMap.floorKey(X);

          // For invalid mission
          if (currentKey == null) {
            // no possibility of doing a quest!
            break;
          } else {
            // Since Mission is valid, update the gold, and the quest list
            current = cmdMap.get(currentKey);
            totalGoldEarned += current.poll();

            if (current.isEmpty()) {
              // mapping still exists despite no quests left!
              // remove the useless mapping
              cmdMap.remove(currentKey);
            }

            X -= currentKey;
          }
        }
        // Output
        io.println(totalGoldEarned);
        io.flush();

      }
    }
  }
}
