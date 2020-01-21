public class GuessingGame {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int ub = 10; // upper bound
		int lb = 1; // lower bound
		boolean honesty = true;

		while (io.hasMoreTokens()) {
			// always comes in a pair consisting:
			// 1. number from 1-10
			// 2. too high/too low/right on
			// We will respond
			// Stan is dishonest/Stan may be honest
			int guess = io.getInt();
			if (guess == 0) {
				io.flush();
				return;
			}

			// for debugging
			if (guess == 11) {
				System.out.println("ub = " + ub + " and lb = " + lb);
				guess = io.getInt();
			}

			String response = io.getWord();
			if (response.equals("right")) {
				// right + on == right on
				response = io.getWord(); // on
				//if (guess >= lb && guess <= ub && lb > 0 && ub < 11) {
				if (honesty) {
					io.println("Stan may be honest");
				} else {
					io.println("Stan is dishonest");
				}

				// THERE IS A NEED TO RESET THE UB AND LB BECAUSE CURRENT GAME ENDS
				ub = 10;
				lb = 1;
				honesty = true;

			} else if (response.equals("too")) {
				// not right on
				response = io.getWord();
				if (response.equals("high")) {
					// current guess is too high
					// cannot be included as ub
					// ub = current guess - 1
					ub = (guess < ub) ? guess - 1 : ub;
					if (ub < lb) {
						honesty = false;
					}
				} else {	
					//(response.equals("too low")
					// current guess is too low
					// cannot be included as lb
					// lb = current guess + 1
					lb = (guess > lb) ? guess + 1 : lb;
					if (ub < lb) {
						honesty = false;
					}
				}
			}
		}

	}
}
