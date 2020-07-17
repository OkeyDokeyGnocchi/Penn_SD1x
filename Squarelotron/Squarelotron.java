public class Squarelotron {
	// instance variables
	int[][] squarelotron;
	int size;
	
	// constructor
	Squarelotron(int n) {
		squarelotron = new int[n][n];
		size = n;
		int number = 1;
		
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				squarelotron[i][j] = number;
				number++;
			}
		}
	}
	
	// clone a squarelotron from the 'main' squarelotron
	// as a starting point
	void copy() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
			    squarelotron[i][j] = this.squarelotron[i][j];
			}
		}
	}

	
	/*
	 * INPUT: int ring (ring number to flip, 1 is outermost)
	 * OUTPUT: new, flipped squareltron
	 * 
	 * creates a new squarelotron and then flips the given ring
	 * upside down
	 */
	Squarelotron upsideDownFlip(int ring) {
		if (ring < 1) {
			throw new NumberFormatException();
		}
		Squarelotron flip = new Squarelotron(size);
		flip.copy();
		
		// used to wrap back around in the main squarelotron
		int wrap = size - 1;
		int start = ring - 1;
		int end = size - ring;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == start || i == end) {
					flip.squarelotron[i][j] = this.squarelotron[wrap - i][j];
				}
				else {
					if (j == start || j == end) {
						flip.squarelotron[i][j] = this.squarelotron[wrap - i][j];
					}
				}
			}
		}
		
		return flip;
		
	}
	
	/*
	 * INPUT: int ring (ring number to flip, 1 is outermost)
	 * OUTPUT: new, diagonally-flipped squareltron
	 * 
	 * creates a new squarelotron and then flips the given ring
	 * diagonally (ij -> ji)
	 */
	Squarelotron mainDiagonalFlip(int ring) {
		if (ring < 1 || ring > 3) {
			throw new NumberFormatException();
		}
		
		Squarelotron diag = new Squarelotron(size);
		diag.copy();
		int start = ring - 1;
		int end = size - ring;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == start || i == end) {
					if (j >= start && j <= end) {
						diag.squarelotron[i][j] = this.squarelotron[j][i];
					}
				}
				else if (i > start && i < end) {
					if (j == start || j == end) {
						diag.squarelotron[i][j] = this.squarelotron[j][i];
					}
				}
			}
		}
		
		return diag;
		
	}
	
	/*
	 * INPUT: int turns (number of 90 degree turns)
	 * OUTPUT: turned squarelotron
	 * 
	 * rotates squarelotron turns * 90 degrees
	 */
	void rotateRight(int turns) {
		int wrap = size - 1;
		
		// turns mod 4 to get remainder, add 360 degrees and
		// mod again to deal with negative numbers by making
		// them 'positive' by looping around clockwise
		// e.g. -1 (90 degree counterclock) == 3 (270 degree clock)
		turns = (turns % 4 + 4) % 4;
		for (int t = 0; t < turns; t++) {
			Squarelotron turn = new Squarelotron(size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					turn.squarelotron[j][wrap - i] = squarelotron[i][j]; 
				}
			}
			this.squarelotron = turn.squarelotron;
		}
	}
	
	
	void printGrid() {
		for (int i = 0; i < squarelotron.length; i++) {
			for (int j = 0; j < squarelotron[i].length; j++) {
				System.out.print(squarelotron[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		Squarelotron squarelo = new Squarelotron(5);
		Squarelotron flipped = squarelo.upsideDownFlip(1);
		Squarelotron diag = squarelo.mainDiagonalFlip(1);
		squarelo.rotateRight(1);
		
	}
	
}