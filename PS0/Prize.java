import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prize {
	public static void main(String[] args) {
		// n items on sale, each of quantity 1
		// buy 2 && total price > x for prize
		// input as: n x prices...
		// output as: number of items with 2-sum <= x
		
		Kattio io = new Kattio(System.in, System.out);
		PriorityQueue<Integer> pricesQueue = new PriorityQueue<>();

		while (io.hasMoreTokens()) {
			int n = io.getInt();
			int x = io.getInt();
			for (int i = 0; i < n; i++) {
				pricesQueue.add(io.getInt()); // fills up PQ
			}
			ArrayList<Integer> prices = new ArrayList<Integer>(pricesQueue);
			if (n <= 0) {
				io.print(0);
			} else if (n == 1) {
				io.print(1);
			} else {
				int a = 0;
				int count = 1; // first item always considered as valid for prize
				int b = 1;
				int priceSum= prices.get(a) + prices.get(b);
				while (b < n && (priceSum <= x)) {
					count++;
					a = b;
					b++;
					priceSum = prices.get(a) + prices.get(b);
				}
				io.print(count);
			}
			io.flush();
		}

	}
}
