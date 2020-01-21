import java.lang.Comparable;

public class Team implements Comparable<Team> {
  public int id;
  public int solved;
  public int penalties;

  public Team(int id, int solved, int penalties) {
    this.id = id;
    this.solved = solved;
    this.penalties = penalties;
  }

  public int compareTo(Team other) {
    if (this.solved != other.solved) {
      return other.solved - this.solved;
    } else {
      if (this.penalties != other.penalties) {
        return this.penalties - other.penalties;
      } else {
        return this.id - other.id; // smaller id first, TreeSet does not allow duplicates:
        //synonymous with same score, same penalty, team 1 ties with the best possible position
      }
    }
  }
}
