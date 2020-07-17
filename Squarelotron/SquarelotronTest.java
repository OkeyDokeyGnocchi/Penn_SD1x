import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquarelotronTest {
	Squarelotron squarelotron;

	@Before
	public void setUp() throws Exception {
		squarelotron = new Squarelotron(4);
	}

	@Test
	public void testSquarelotron() {
		squarelotron = new Squarelotron(4);
		assertNotNull(squarelotron);
		assertEquals(squarelotron.size, 4);
		squarelotron = new Squarelotron(5);
		assertEquals(squarelotron.size, 5);
	}

	@Test
	public void testUpsideDownFlip() {
		squarelotron = new Squarelotron(5);
		Squarelotron flipped = squarelotron.upsideDownFlip(1);
		assertEquals(flipped.size, squarelotron.size);
		assertEquals(flipped.squarelotron[0][0], squarelotron.squarelotron[4][0]);
		assertEquals(flipped.squarelotron[4][4], squarelotron.squarelotron[0][4]);
	}

	@Test
	public void testMainDiagonalFlip() {
		squarelotron = new Squarelotron(5);
		Squarelotron flipped = squarelotron.mainDiagonalFlip(1);
		assertEquals(flipped.size, squarelotron.size);
		assertEquals(flipped.squarelotron[0][0], squarelotron.squarelotron[0][0]);
		assertEquals(flipped.squarelotron[0][4], squarelotron.squarelotron[4][0]);
		assertEquals(flipped.squarelotron[4][0], squarelotron.squarelotron[0][4]);
	}

	@Test
	public void testRotateRight() {
		squarelotron = new Squarelotron(5);
		squarelotron.rotateRight(2);
		assertEquals(squarelotron.squarelotron[0][0], 25);
		assertEquals(squarelotron.squarelotron[4][0], 5);
		squarelotron = new Squarelotron(5);
		squarelotron.rotateRight(-2);
		assertEquals(squarelotron.squarelotron[0][0], 25);
		assertEquals(squarelotron.squarelotron[4][0], 5);
		squarelotron = new Squarelotron(5);
		squarelotron.rotateRight(3);
		assertEquals(squarelotron.squarelotron[0][0], 5);
		assertEquals(squarelotron.squarelotron[4][4], 21);
	}

}
