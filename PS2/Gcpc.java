import java.util.TreeSet;
import java.util.SortedSet;

public class Gcpc {
  public static void main(String[] args) {

    Kattio io = new Kattio(System.in, System.out);
    // TreeSet Data Structure:
    // TreeSet is a DS which we can use to obtain elements wrt an element
    // ie we can get a subset all greater/equal or all smaller/equal
    // hence need to have a class that is Comparable
    // However, also need to identify the Team, so attach an id,
    // besides the score and the penalties

    // Set Up
    int n = io.getInt(); // no. of teams
    int m = io.getInt(); // no. of events

    // to store an array version of TreeSet for easy reference and updating later,
    // since we can't really access the Team in TreeSet using its higher/lower methods
    // due to User-defined class
    Team[] buffer = new Team[n];

    // Focus on TreeSet to exploit its order
    TreeSet<Team> teams = new TreeSet<>(); // to contain all the teams
    // Fill up TreeSet with all Teams first, which we can update later on
    for (int team = 1; team <= n; team++) {
      Team create = new Team(team, 0, 0);
      buffer[team - 1] = create; // Team n is at buffer[n-1]
      teams.add(create); // id, solved, penalties
    }

    // Processing
    int t;
    int p;
    int rank = 0; // live rank count, will keep track of how many in front of Team 1
    // hence rank begins at 0, and we will add 1 when finally printing to ensure
    // consistency

    for (int event = 0; event < m; event++) {

      // Updating of Teams
      t = io.getInt();
      p = io.getInt();
      // Extract current team of interest
      // Obtain team-specific previous data from it
      Team prev = buffer[t - 1];
      teams.remove(prev);
      // Create a new Team based on the previous data with current changes
      Team updated = new Team(prev.id, prev.solved + 1, prev.penalties + p);
      // Update the TreeSet to use for comparison for Ranking
      // and update the buffer array for next easy reference
      buffer[t - 1] = updated;
      teams.add(updated);

      // Updating the Ranking if Teams Relativity changes
      // Need to obtain reference first to obtain from TreeSet
      // Team 1 has index 1-0 = 0
      Team favourite = buffer[0];
      if (t != 1) {
        if ((favourite.compareTo(prev) < 0) && (favourite.compareTo(updated) > 0)) {
          // Team 1 initially better than this team, now Team 1 is worse than this team
          // Team 1 has one more Team in front of it
          // Hence Teams Relativity changes
          rank++;
        } else {
          // else for !(prev.compareTo(favourite) > 0) && (updated.compareTo(favourite) < 0),
          // this Team still remains in front/behind Team 1
          // the ranking of Team 1 does not change as no team has relatively changed
          // do nothing
        }
      } else {
        // If Team 1 moved, it may overtake any number of other teams
        // We don't know this so we recalculate all in front of it
        SortedSet<Team> higherTeams = teams.headSet(favourite);
        rank = higherTeams.size();
      }
      io.println(rank + 1);
    }
    io.flush();

  }
}
