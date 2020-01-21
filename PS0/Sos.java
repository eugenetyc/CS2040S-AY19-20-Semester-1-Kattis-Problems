import java.util.PriorityQueue;
import java.util.ArrayList;

public class Sos {
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		ArrayList<PriorityQueue<Name>> allQ = new ArrayList<>();
		boolean continuing = true;

		while (continuing && io.hasMoreTokens()){
			int n = io.getInt();
			
			if (n == 0) {
				continuing = false;
			} else {
				// Complete aln, and all aln to the print queue
				PriorityQueue<Name> aln = new PriorityQueue<>();
				for (int j = 0; j < n; j++) {
					aln.add(new Name(io.getWord(), j));
				}
				allQ.add(aln);
			}

		}

		// Process print queue
		for (int i = 0; i < allQ.size(); i++) {
			PriorityQueue<Name> names = allQ.get(i);
			while(!names.isEmpty()) {
				io.println(names.poll().name);
			}
			if (i != allQ.size() - 1) {
				io.println(); // sep line
			}
		}
		io.flush();
	}
}
