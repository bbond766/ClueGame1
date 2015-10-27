package clueGame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GamePlay_tests {

	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	// The methods that test invalid config files will have a local 
	// Board variable, so will not use this
	private static Board board;

	@Before
	public void setUp() throws Exception {
		// Create a new Board using the valid files. Note that
		// the default filenames must be attributes of the Board class. 
		board = new Board();
		// Initialize will load BOTH config files 
		board.initialize();
	}

	@Test
	public void testAccusationCorrectness() {
		//solution finalized during deal, which is called by initialize method
		Player player = board.getPlayers().get(0);
		if (board.getCellAt(player.getColumn(), player.getRow()).isRoom()){
			
		}

	}

}
