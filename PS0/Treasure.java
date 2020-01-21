public class Treasure {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int R = io.getInt();
		int C = io.getInt();
		
		// Player setup
		int count = 0;
		int playerX = 0;
		int playerY = 0;
		String playerMove = "";

		// Treasure and field setup
		String[][] arr = new String[R][C];
		for (int i = 0; i < R; i++) {
			String item = io.getWord();
			char[] cArr = item.toCharArray();
			for (int j = 0; j < C; j++) {
				arr[i][j] = "" + cArr[j];
			}
		}

		try {
			while (!playerMove.equals("stepped") && count < (R*C)) {
				// Not OOB, is somewhere in the field
				playerMove = arr[playerY][playerX];
				switch (playerMove) {
					case "N":
						arr[playerY][playerX] = "stepped";
						playerY--; // reversed
						count++;
						break;
					case "S":
						arr[playerY][playerX] = "stepped";
						playerY++; // reversed
						count++;
						break;
					case "E":
						arr[playerY][playerX] = "stepped";
						playerX++;
						count++;
						break;
					case "W":
						arr[playerY][playerX] = "stepped";
						playerX--;
						count++;
						break;
					case "T":
						io.print(count);
						io.flush();
						return;
					default:
						break;
				}
			}
			io.print("Lost");
			io.flush();
			return;
		} catch (Exception e) {
			io.print("Out");
			io.flush();
		}

	}
}
