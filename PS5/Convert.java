public class Convert {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    int m = io.getInt();
    io.flush();
    String s = "" + n + " " + m + "\n";
    for (int i = 0; i < m; i++) {
      int u = io.getInt();
      int v = io.getInt();
      int w = io.getInt();
      s += (u-1) + " " + (v-1) + " " + w + "\n";
    }
    io.println("begins here");
    io.println(s);
    io.flush();
  }
}
