import java.util.*;

public class WhackAMole {
	// variables
	int score = 0;
	int molesLeft = 0;
	int attemptsLeft = 0;
	char[][] moleGrid;

	// constructor
	WhackAMole(int numAttempts, int gridDimension) {
		attemptsLeft = numAttempts;
		moleGrid = new char[gridDimension][gridDimension];
		
		// fill that grid with * for empty spaces
		for (char[] row: moleGrid) {
			Arrays.fill(row, '*');
		}
	}

	// methods
	
	/**
	 * places moles on the grid using x,y coords
	 * @param x - the x coordinate to place the mole at (row)
	 * @param y - the y coordinate to place the mold at (column)
	 * @return - if mole doesn't already exist and can be placed, return true
	 * 				and increment the number of moles left
	 * 				otherwise, return false
	 */
	boolean place(int x, int y) {
		try {
			if (moleGrid[x][y] == '*') {
				moleGrid[x][y] = 'M';
				molesLeft++;
				return true;
			}
			else {
				return false;
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	/**
	 * attempt to whack the mole at the given coordinate. if there is a mole,
	 * 		add 1 to score, subtract 1 mole left, change the 'M' to 'W' at
	 * 		location. either way, attempts left is decremented by 1.
	 * @param x - the x coordinate to whack (row)
	 * @param y - the y coordinate to whack (column)
	 */
	void whack(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			score++;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		attemptsLeft--;
	}
	
	/**
	 * prints the grid to the user, replacing 'M's with '*'s. 'W' shows
	 *  	all moles that have been whacked.
	 */
	void printGridToUser() {
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				if (moleGrid[i][j] == 'M') {
					System.out.print('*' + " ");
				}
				else {
					System.out.print(moleGrid[i][j] + " ");
				}

			}
			System.out.println();
		}
	}
	
	/**
	 * prints the grid as it really is (shows where moles are).
	 */
	void printGrid() {
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				System.out.print(moleGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// initialize the game with 50 attempts and 10x10 grid
		WhackAMole game = new WhackAMole(50, 10);
		
		// generate random ints of <=9 for coords to get 10 moles placed
		Random rand = new Random();
		while (game.molesLeft < 10) {
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
			game.place(x, y);	
		}
		
		// print some info for the user
		// welcome and all that jazz
		System.out.println("Welcome! It's time to whack some moles!");
		System.out.println("Type your selected coordinates to whack!");
		System.out.println("(Use the coordinates -1, -1 to quit)");
		
		// end game if attempts hits 0 or moles hits 0
		while (game.attemptsLeft > 0 && game.molesLeft > 0) {
			// inform user of attempts and moles remaining
			System.out.println("\n" + game.attemptsLeft + " attempts and "
					+ game.molesLeft + " moles remain!");
			
			/* create the scanner for user input, delimiters are whitespace
			 * and commas
			*/
			Scanner input = new Scanner(System.in).useDelimiter(",|\\s+");
			
			if (input.hasNext()) {
				int x = input.nextInt();
				int y = input.nextInt();
				
				// check if user input is within our grid
				if (x < 0 || x > 9 || y < 0 || y > 9) {
					
					// quit if user inputs -1, -1, print grid, exit
					if (x == -1 && y == -1) {
						System.out.println("You've chosen to quit.\n");
						game.printGrid();
						System.exit(0);
					}
					// if input is just invalid, inform player
					else {
						System.out.println("Your coordinates are invalid!");
					}
				}
				// if input is good: attempt whack, show grid
				else {
					game.whack(x, y);
					game.printGridToUser();
				}
			}
		}
		// end game if attempts = 0
		if (game.attemptsLeft == 0) {
			System.out.println("The moles have won...");
		}
		// end game if moles = 0
		else if (game.molesLeft == 0) {
			System.out.println("You defeated the moles!");
		}
	}
}