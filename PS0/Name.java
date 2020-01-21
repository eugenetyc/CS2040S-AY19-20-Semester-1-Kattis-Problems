public class Name implements Comparable<Name> {
	
	public String name;
	public int index;

	public Name(String s, int index) {
		this.name = s;
		this.index = index;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	public int compareTo(Name other) {
		String ss1 = this.name.substring(0,2);
		String ss2 = other.name.substring(0,2);
		if (ss1.equals(ss2)) {
			return this.index - other.index;
		}
		return ss1.compareTo(ss2);
	}

}
