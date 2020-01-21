import java.util.Collections;
import java.util.ArrayList;

public class SortOf {
	public static void main(String[] args) {
		// for firstTime, don't put a separating line
		boolean firstTime = true;
		// Take in number of names first, then take in names, then arrange names
		Kattio io = new Kattio(System.in, System.out);
		ArrayList<ArrayList<Name>> allSets = new ArrayList<>();

		while (io.hasMoreTokens()) {
			int n = io.getInt();
			if (n != 0) {
				// fill up AL
				ArrayList<Name> qOne = new ArrayList<>();
				allSets.add(qOne);
				// for n times, we take names, store, then process.
				for (int i = 0; i < n; i++) {
					qOne.add(new Name(io.getWord()));
				}		
				// to sort all in ArrayList	
				Collections.sort(qOne);		
			} else {
				// n = 0 so it is time to stop collecting data
				// and initiate the printing of output
				for (int j = 0; j < allSets.size(); j++) {
					ArrayList<Name> current = allSets.get(j);
					for (int k = 0; k < current.size(); k++) {
						if (k != 0) {
							io.print("\n" + current.get(k).name);
						} else {
							io.print(current.get(k).name);
						}
					}
					// if last AL, don't print separating line
					if (j < allSets.size() - 1) {
						io.println();
						io.println();
					}
				}
				io.flush();
				return;
			}
		}

	}
}
