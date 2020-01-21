public class Svada {

	public static void main(String[] args) {
		// Essentially, this question wants us to find the time t
		// such that in time t, plucker monkeys give X coconuts
		// and in time (T - t), cracker monkeys crack X coconuts or more

		Kattio io = new Kattio(System.in, System.out);

		int T = io.getInt(); // this is total time

		int pluckers = io.getInt(); // these increase total number of coconuts
		int[] pluckerDelays = new int[pluckers];
		int[] pluckerCycles = new int[pluckers];
		for (int p = 0; p < pluckers; p++) {
			pluckerDelays[p] = io.getInt();
			pluckerCycles[p] = io.getInt();
		}
		int crackers = io.getInt(); // these decrease the total number of coconuts
		int[] crackerDelays = new int[crackers];
		int[] crackerCycles = new int[crackers];
		for (int c = 0; c < crackers; c++) {
			crackerDelays[c] = io.getInt();
			crackerCycles[c] = io.getInt();
		}

		// Main work done here: Binary Search
		int low = 1;
		int high = T;
		int t = 0;
		int totalPlucked = 0;
		int totalCracked = 0;

		while (high >= low) {

			// New value of t
			totalPlucked = 0;
			totalCracked = 0;
			t = (low + high)/2;

			// Calculate total plucked, and total cracked
			for (int i = 0; i < pluckers; i++) {
				totalPlucked += (pluckerDelays[i] <= t)
				       ? 1 + (t - pluckerDelays[i])/pluckerCycles[i]
				       : 0;
			}
			for (int j = 0; j < crackers; j++) {
				totalCracked += (crackerDelays[j] <= (T - t))
					? 1 + (T - t - crackerDelays[j])/crackerCycles[j]
					: 0;
			}

			// Compare totalPlucked and totalCracked
			if (totalPlucked == totalCracked) {
				break;
			} else if (totalPlucked > totalCracked && high - low == 1) {
				t = high;
				break;
			} else if (totalCracked > totalPlucked && high - low == 1) {
				t = low;
				break;
			} else if (totalPlucked > totalCracked) {
				high = t;
			} else if (totalPlucked < totalCracked) {
				low = t;
			}
		}

		io.println(t);
		io.flush();
	}

}
