public class Ex4 {

	public static final boolean LAND = true;
	public static final boolean WATER = false;
	
	public static boolean[][] map = {{WATER, LAND, WATER, LAND},
					 {LAND, LAND, WATER, WATER},
					 {WATER, WATER, LAND, WATER},
					 {WATER, WATER, LAND, WATER}};
	
	public static void main(String[] args) {
		System.out.print("Number of islands in the map: ");
		System.out.println(countIslands(map));
	}
	
	/**
	 * iterate over each cell of the map.
	 * when encountering land, mark all of its consecutive land neighbors
	 * to form one island
	 * @param map boolean map where true is land and false is water
	 * @return the number of islands found
	 */
	public static int countIslands(boolean[][] map) {
		int numberOfIslands = 0;
		
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map.length; col++) {
				if (map[row][col]) {
					exploreNeighbors(row, col, map);
					numberOfIslands++;
				}
			}
		}
		return numberOfIslands;
	}

	/**
	 * Mark adjacent land tiles of specific cell coordinate in the map
	 * as contiguous land to form an island and avoid counting it twice.
	 * @param row
	 * @param col
	 * @param map
	 */
	private static void exploreNeighbors(int row, int col, boolean[][] map) {
		//check valid coordinations
		if (!validRowColumn(row, col, map)) {
			return;
		}
		
		//if land was found, mark it and all of its land neighbors
		//so they will be counted as one island
		if (map[row][col]) {
			map[row][col] = false;
			exploreNeighbors(row - 1, col, map);
			exploreNeighbors(row + 1, col, map);
			exploreNeighbors(row, col - 1, map);
			exploreNeighbors(row, col + 1, map);
		}
	}
	
	/**
	 * check that a cell of type map[row][col] is not out of bounds
	 * @param row
	 * @param col
	 * @param map
	 * @return true if inbounds, false otherwise
	 */
	private static boolean validRowColumn(int row, int col, boolean[][] map) {
		// check out of bounds rows wise
		if (row < 0 || row >= map.length) {
			return false;
		}

		// check out of bounds columns wise
		if (col < 0 || col >= map[row].length) {
			return false;
		}

		return true;
	}
}
