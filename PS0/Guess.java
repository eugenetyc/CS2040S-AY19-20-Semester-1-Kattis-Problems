public class Guess {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int n = io.getInt();
		boolean lying = false;
		int ub = 10;
		int lb = 1;

		while (n != 0) {
			if (ub < lb) {
				lying = true;
			}
			// processing response str
			String firstWord = io.getWord(); // irrelevant
			String key = io.getWord(); // relevant
			
			if (key.equals("high")) {
				// n is too high
				// number is somewhere between n-1 and lb
				if (ub >= lb) {
					ub = (ub < n) ? ub : n-1;
				}
			} else if (key.equals("low")) {
				// n is too low
				// number is somewhere between ub and n+1
				if (ub >= lb) {
					lb = (lb > n) ? lb : n+1;
				} 
			} else {
				// n is the current answer
				
				// output
				if (lying || n < lb || n > ub) {
					io.println("Stan is dishonest");
				} else {
					io.println("Stan may be honest");
				}
				io.flush();

				// reset game
				ub = 10;
				lb = 1;
				lying = false;
			}

			// update n for next step/game
			n = io.getInt();
		}	

	}
}
