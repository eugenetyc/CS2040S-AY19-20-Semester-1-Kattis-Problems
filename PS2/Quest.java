import java.lang.Comparable;

public class Quest implements Comparable<Quest> {
  public int e;
  public int g;

  public Quest(int e, int g) {
    this.e = e;
    this.g = g;
  }

  public int compareTo(Quest other) {
    if (this.e != other.e) {
      return other.e - this.e;
    } else {
      return other.g - this.g;
    }
  }
}
