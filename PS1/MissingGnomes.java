import java.util.ArrayList;

public class MissingGnomes {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		// Data collection

		int n = io.getInt(); // orig no.
		int m = io.getInt(); // remaining no.
		if (n == m) {
			// none removed, just follow same order
			for (int p = 0; p < n; p++) {
				io.println(io.getInt());
			}
		} else {
			ArrayList<Integer> q = new ArrayList<>(); // removed
			ArrayList<Integer> q2 = new ArrayList<>(); // remaining
			// n of them, eventually remove remaining
			for (int g = 1; g <= n; g++) {
				q.add(g); // fill up q with all possible
			}
			// m remainders to be removed
			for (int i = 0; i < m; i++) {
				int current = io.getInt();
				q.remove(q.indexOf(current)); // remove those remaining
				q2.add(current); // add those remaining
			}

			// Data processing and Output
			int qPointer = 0; // of removed gnomes
			int q2Pointer = 0; // of remaining gnomes
			while (qPointer < q.size() && q2Pointer < q2.size()) {
				// q now is a list of all the numbers not included
				// q2 is a list of all numbers included
				// compare q1 with q2. Print the smaller head. Increament head of specific q/q2.
				int qValue = q.get(qPointer);
				int q2Value = q2.get(q2Pointer);
				if (qValue < q2Value) {
					io.println(qValue);
					qPointer++;
				} else {
					io.println(q2Value);
					q2Pointer++;
				}
			}
			// if either fully processed, print all of the other
			while (qPointer < q.size()) {
				io.println(q.get(qPointer));
				qPointer++;
			}
			while (q2Pointer < q2.size()) {
				io.println(q.get(q2Pointer));
				q2Pointer++;
			}
		}
		io.flush();
	}
}
