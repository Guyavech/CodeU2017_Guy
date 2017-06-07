import java.util.TreeSet;

public class Ex3 {
	
	private int rows;
	private int columns;
	private char[][] matrix;
	
	//a set for final valid words in the dictionary
	private TreeSet<String> wordsInMatrix = new TreeSet<>();
	
	private Dictionary dictionary;

	/**
	 * Constructor
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param matrix Matrix of rows x columns filled with characters of type char
	 * @param dictionary a Dictionary object
	 */
	public Ex3(int rows, int columns, char[][] matrix, Dictionary dictionary) {
		this.rows = rows;
		this.columns = columns;
		this.matrix = matrix;
		this.dictionary = dictionary;

		wordsInMatrix = findWords(rows, columns, matrix, dictionary);
	}

	/**
	 * A method that iterates over the characters matrix and
	 * initiate on each cell the check-up
	 * @param rows
	 * @param columns
	 * @param matrix
	 * @param dictionary
	 * @return a set of all words in found in the grid
	 */
	public TreeSet<String> findWords(int rows, int columns, char[][] matrix, Dictionary dictionary) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				checkPath(i, j, new StringBuilder(), new boolean[rows][columns]);
			}
		}	
		return this.wordsInMatrix;
	}
	
	/**
	 * Check if a valid path going out of this cell
	 * builds into a valid dictionary word
	 * @param i current row index
	 * @param j current column index
	 * @param sb a StringBuilder Object
	 * @param visitedCells - a boolean matrix of visited cells on this path
	 */
	private void checkPath(int i, int j, StringBuilder sb, boolean[][] visitedCells) {
		//check that we're inside the grid
		if (!validRowColumn(i,j)) {
			return;
		}
		
		//check that this cell hasn't been visited before
		if (visitedCells[i][j]) {
			return;
		}
	
		String word = sb.append(matrix[i][j]).toString();
		
		//check if it's a valid dictionary word
		if (this.dictionary.isWord(word)) {
			this.wordsInMatrix.add(word);
		}
		
		// if this word is also a prefix of another word
		// traverse into its neighboring cells
		if (this.dictionary.isPrefix(word)) {
			visitedCells[i][j] = true;
			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					//get 'sb' ready for garbage collection
					sb = null;
					
					//traverse to neighbor
					checkPath(x, y, new StringBuilder(word), visitedCells);
				}
			}
		} 
	}
	
	/**
	 * @param row
	 * @param col
	 * @return true if [row,col] is a legit cell in the characters grid
	 */
	private boolean validRowColumn(int row, int col) {
		if (row < 0 || row >= this.rows) {
			return false;
		}

		if (col < 0 || col >= this.columns) {
			return false;
		}

		return true;
	}

	public TreeSet<String> getWordsInMatrix() {
		return this.wordsInMatrix;
	}	
}
