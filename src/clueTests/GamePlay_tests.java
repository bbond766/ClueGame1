package clueTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Player;
import clueGame.Solution;

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
		Solution solution = new Solution("Ms. White", "Library", "Lead Pipe");
		
		Player player = board.getPlayers().get(0);
		player.makeSuggestion(board, board.getCellAt(player.getColumn(), player.getRow()));
		Solution suggestion = player.getSuggestion();
		
		if (board.getCellAt(player.getColumn(), player.getRow()).isRoom()){
			char i = board.getCellAt(player.getColumn(), player.getRow()).getInitial();
			String val = board.getRooms().get(i);
			assertEquals(val, suggestion.getRoom());
			assertTrue(suggestion.getPerson() == solution.getPerson());
			assertTrue(suggestion.getRoom() == solution.getRoom());
			assertTrue(suggestion.getWeapon() == solution.getWeapon());
		}
		
	}
	
	//Tests that player returns the one card to disprove the suggestion
	@Test
	public void testDisproveSuggestionOnePossible(){
		
	}
	
	//Tests that player randomly returns one of the cards to disprove the suggestion
	@Test
	public void testDisproveSuggestionTwoPossible(){
		
	}
	
	@Test
	public void testOrder(){
		
	}
	
	//Tests that the current player does not attempt to disprove suggestion
	@Test
	public void testAccusingPlayerNoCardReturn(){
		
	}
	
	@Test
	public void testHumanPlayer(){
		
	}
	
	@Test
	public void testTargetSelection(){
		
	}
	
	@Test
	public void testCompPlayerOneSuggestion(){
		
	}
	
	@Test
	public void testCompPlayerRandPossible(){
		
	}

}
