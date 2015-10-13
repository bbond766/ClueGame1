package clueTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.DoorDirection;

public class loadingTest {
	
	Board boardTest;
	@Before
	public void setUpBoard()
	{
		boardTest = new Board("board.csv", "key.txt");
		boardTest.initialize();
	}
	@Test
	public void test() {
		assertEquals(9, boardTest.getRooms().size());
		assertEquals("Dining room",boardTest.getRooms().get('D'));
		
		assertEquals(boardTest.BOARD_SIZE, boardTest.getNumColumns());
		assertEquals(boardTest.BOARD_SIZE, boardTest.getNumRows());
		assertEquals(DoorDirection.DOWN, boardTest.getCellAt(11, 6).getDoorDirection());
		assertEquals(DoorDirection.NONE, boardTest.getCellAt(0, 0).getDoorDirection());
		assertFalse(boardTest.getCellAt(0, 0).isDoorway());
	}
	@Test(expected=BadConfigFormatException.class)
	public void testBadConfigFormatException() throws BadConfigFormatException
	{
		
		boardTest = new Board("fdsakjhfka.csv", "fdajklf.txt");
		boardTest.loadBoardConfig();
		boardTest.loadRoomConfig();
		
		
	}

}
