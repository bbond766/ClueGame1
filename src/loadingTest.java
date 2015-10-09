import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class loadingTest {
	
	IntBoard boardTest;
	@Before
	public void setUpBoard()
	{
		boardTest.initialize();
	}
	@Test
	public void test() {
		assertEquals(9, boardTest.getRooms().size());
		assertEquals("Dining Room",boardTest.getRooms().get('D'));
		
		assertEquals(boardTest.BOARD_SIZE, boardTest.getNumColumns());
		assertEquals(boardTest.BOARD_SIZE, boardTest.getNumRows());
		assertEquals(DoorDirection.DOWN, boardTest.getCell(11, 6).getDirection());
		assertEquals(DoorDirection.NONE, boardTest.getCell(0, 0).getDirection());
		assertFalse(boardTest.getCell(0, 0).isDoorway());
	}
	@Test(expected=BadConfigFormatException.class)
	public void testBadConfigFormatException()
	{
		
		try {
			boardTest.loadBoardConfigFile("filename");
			boardTest.loadRoomConfigFile("filename");
		} catch (BadConfigFormatException e) {
			fail("Throws an exception.");
		}
		
	}

}
