public class Problem {

	public static void main(String[] args) {
		
		Kattio io = new Kattio(System.in, System.out);
		
		while (io.hasMoreTokens()) {
			long a = io.getLong();
			long b = io.getLong();
			long diff = a>b ? a-b : b-a;
			io.println(diff);
		}

		io.flush();
	}

}
