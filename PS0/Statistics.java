public class Statistics {

	public static void main(String[] args) {
		
		Kattio io = new Kattio(System.in, System.out);

		// To update as cases increase
		int caseNumber = 1;

		while (io.hasMoreTokens()) {
			
			// Find size of single line input
			int size = io.getInt();

			// For first number in single line input
			int max = io.getInt();
			int min = max;
			int range = max - min;

			// For more than 1 numbers in single line input
			if (size > 1) {

				// compare and update min and max values
				for (int i = 0;  i < size - 1; i++) {
					
					int value = io.getInt();
					min = value<min ? value : min;
					max = value>max ? value : max;
					range = max - min;
				
				}

			}

			io.println("Case " + caseNumber + ": " + min + " " + max + " " + range);
			caseNumber++;
		}

		io.flush();
	}

}
