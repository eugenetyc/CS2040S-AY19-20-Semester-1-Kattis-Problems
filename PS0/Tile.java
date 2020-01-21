public class Tile {
	boolean arrived;
	int x;
	int y;
	String move;

	public Tile(int x, int y, String s) {
		this.arrived = false;
		this.x = x;
		this.y = y;
		this.move = s;
	}

	public boolean lostCheck() {
		return this.arrived; // if arrived before, will lead to a circle. Lost.
	}
}
