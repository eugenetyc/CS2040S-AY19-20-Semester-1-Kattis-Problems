public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		var l = "lol";
		System.out.println(l); //jdk 10 and above can use var type, must be initialised in same line
		// jshell, /*ajshdfkjdskd*/
		// 
		// I/O:
		// Kattio input = new Kattio(System.in); // getInt(), getDouble(), getWord(), hasMoreTokens();
		// int i = input.getInt();
		// double d or float d = input.getDouble();
		// String s = input.getWord();
		// Note: Don't use Scanner scanner = new Scanner(System.in) IS VERY SLOW!!!
		// 
		// Printing:
		// System.out.println("...")/ System.out.printf("... %.3f"\n, number to make into 3dp)
		// Kattio input = new Kattio(System.in) and then 
		// input.printf();
		// ***input.flush(); // to remove 4kb 'cache'***
		// ***input.hasMoreTokens()*** same as scanner.hasNext()
		// input.println() same thing
		// The %.3f inside will be replaced by the value after the comma.
		// Use \n for newline
		//
		// Structures:
		// import java.util.ArrayList;
		// ArrayList<String> as = new ArrayList<>(); // expandable size. note ArrayList<primitiveType> cannot.
		// as.get(indexNumber), as.add("wejrfksel")
		// int[] lol = new int[100]; // fixed size
		// int -> Integer, char -> Character; double => Double, long => Long, boolean => Boolean
		//
		// Types:
		// float, int, double, long, String, char, boolean, void
		// Operations:
		// Math.pow(12345), % for negative gives negative
		// Math.abs(a - b);
		// Loops:
		// for (int i = 0; i < 5; i++), while (i == 10)
		//
		// java Programme < data.in
		// :%s/previous/next/g
	}
}
