import java.util.PriorityQueue;

public class Narwhale {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int n = io.getInt();
		int x = io.getInt();
		int count = (n <= 1) ? n : 1;

		if (n > 1) {
			// store all n ints in sorted
			PriorityQueue<Integer> ns = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				ns.add(io.getInt());
			}

			// process
			int a = ns.poll(); //count already 1 since a cfm accepted
			int b = ns.poll();
			while (a+b <= x) {
				count++; // b is ok
				a = b;
				if (!ns.isEmpty()) {
					b = ns.poll();
				} else {
					break;
				}
			}
		}

		io.print(count);
		io.flush();

	}
}
