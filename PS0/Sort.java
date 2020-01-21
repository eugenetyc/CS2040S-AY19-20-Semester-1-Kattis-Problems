import java.util.PriorityQueue;
import java.lang.Comparable;

public class Sort {

	public static void main(String[] args) {
	
		Kattio io = new Kattio(System.in, System.out);
		boolean firstScan = true;
		
		while (io.hasMoreTokens()) {
			int n = io.getInt();
			// if 0, break
			if (n == 0) {
				break;
			}

			// else add to PQ, and print from PQ with stated order
			PriorityQueue<Name> names = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				Name s = new Name(io.getWord());
				names.add(s);
			}

			if (!firstScan) {
				io.println();
				io.println();
			} 
			while (!names.isEmpty()) {
				if (names.size() == 1) {
					io.print(names.poll());
				} else {
					io.println(names.poll());
				}
				firstScan = false;
			}

		}
		io.flush();
	
	}
}
