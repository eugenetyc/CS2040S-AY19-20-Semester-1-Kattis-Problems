public class Moose {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		// To store String result
		String result = "";

		// Main work done here
		while (io.hasMoreTokens()) {
			int left = io.getInt();
			int right = io.getInt();
			if (left == right) {
				result += (left!=0) ? "Even " + (2*left) : "Not a moose";
			} else {
				result += "Odd " + 2*(left>right ? left:right);
			}
		}

		// Finally print the result
		io.println(result);

		// Program end
		io.flush();	

	}
}
