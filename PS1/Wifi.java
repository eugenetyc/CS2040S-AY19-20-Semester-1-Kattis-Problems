import java.util.Arrays;

public class Wifi {
	public static void main(String[] args) {
		// inputs
		Kattio io = new Kattio(System.in, System.out);
		int testcases = io.getInt();
		for (int t = 0; t < testcases; t++) {
			int n = io.getInt(); // access pts
			int m = io.getInt(); // houses
			int[] houses = new int[m];
			for (int h = 0; h < m; h++) {
				houses[h] = io.getInt();
			}

			Arrays.sort(houses);

			// outputs
			double low = 0; // the best case is the house closest
			double high = houses[m - 1]; // the worst case is the house furthest
			double result = 0; // to store the answer
			while (low < high) {
				double mid = (low + high) / 2;
				if (Wifi.coversAllHouses(houses, mid, n)) {
					result = mid; // to remember best result so far
					high = mid - 0.02; // 0.02 as some small enough significant value
				} else {
					low = mid + 0.02;
				}
			}
			int whole = (int) result; // 4
			double decimals = result - whole; // 0.529
			int tdp = (int) (decimals * 100); // 52.9
			int sdp = tdp%10;
			int fdp = tdp/10;
			if (sdp >= 5) {
				fdp++;
			}
			result = whole + ((double) fdp) / 10;
			io.println(result);
			io.flush();
		}

	}

	public static boolean coversAllHouses(int[] houses, double radius, int ap) {
		double coveredDistance = houses[0] + 2 * radius;
		ap--;
		boolean overall = true;
		for (int k = 0; k < houses.length; k++) {
			// check all except last house
			if (houses[k] > coveredDistance) {
				// not in covered distance, place new ap at 1x radius from current houses
				// hence coverageDistance = current house + 2 radius
				ap--;
				coveredDistance = houses[k] + 2 * radius;
			} else {
				// within covered distance, is ok. ONLY FOR NON-LAST-HOUSE
			}
		}
		if (ap < 0) {
			return false;
		} else {
			return true;
		}
	}
}
