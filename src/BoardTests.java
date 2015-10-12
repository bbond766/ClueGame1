import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class BoardTests {
	IntBoard boardTest;
	@Before
	public void intBoardSetUp() {
		boardTest = new IntBoard();
		boardTest.calcAdjancencies();
	}

	@Test
	public void boardAdjacencyTests() {
		//Locations with only walkways as adjacent locations
		LinkedList<BoardCell> allWalkways = boardTest.getAdjList(6,17);
		assertTrue(allWalkways.contains(boardTest.getCell(6,16)));
		assertTrue(allWalkways.contains(boardTest.getCell(7,17)));
		assertTrue(allWalkways.contains(boardTest.getCell(6,18)));
		assertTrue(allWalkways.contains(boardTest.getCell(5,17)));
		
		//Locations that are at each edge of the board
		LinkedList<BoardCell> topEdgeTest = boardTest.getAdjList(7,0);
		assertTrue(topEdgeTest.contains(boardTest.getCell(6,0)));
		assertTrue(topEdgeTest.contains(boardTest.getCell(8,0)));
		assertTrue(topEdgeTest.contains(boardTest.getCell(7,1)));
		
		LinkedList<BoardCell> rightEdgeTest = boardTest.getAdjList(25,4);
		assertTrue(rightEdgeTest.contains(boardTest.getCell(25,3)));
		assertTrue(rightEdgeTest.contains(boardTest.getCell(25,5)));
		assertTrue(rightEdgeTest.contains(boardTest.getCell(24,4)));
		
		LinkedList<BoardCell> bottomEdgeTest = boardTest.getAdjList(2,25);
		assertTrue(bottomEdgeTest.contains(boardTest.getCell(2,24)));
		assertTrue(bottomEdgeTest.contains(boardTest.getCell(3,25)));
		assertTrue(bottomEdgeTest.contains(boardTest.getCell(1,25)));
		
		LinkedList<BoardCell> leftEdgeTest = boardTest.getAdjList(0,8);
		assertTrue(leftEdgeTest.contains(boardTest.getCell(0,7)));
		assertTrue(leftEdgeTest.contains(boardTest.getCell(1,8)));
		assertTrue(leftEdgeTest.contains(boardTest.getCell(0,9)));
		
		//Locations that are beside a room cell that is not a doorway
		LinkedList<BoardCell> adjRoomTest1 = boardTest.getAdjList(14,4);
		assertTrue(adjRoomTest1.contains(boardTest.getCell(14,3)));
		assertTrue(adjRoomTest1.contains(boardTest.getCell(15,4)));
		assertTrue(adjRoomTest1.contains(boardTest.getCell(14,5)));
		
		LinkedList<BoardCell> adjRoomTest2 = boardTest.getAdjList(21,4);
		assertTrue(adjRoomTest2.contains(boardTest.getCell(22,4)));
		assertTrue(adjRoomTest2.contains(boardTest.getCell(21,5)));
		
		//Locations that are adjacent to a doorway with needed direction (i.e., the adjacency list will include the doorway)
		LinkedList<BoardCell> adjDoorUpTest = boardTest.getAdjList(1,13);
		assertTrue(adjDoorUpTest.contains(boardTest.getCell(1,12)));
		assertTrue(adjDoorUpTest.contains(boardTest.getCell(2,13)));
		assertTrue(adjDoorUpTest.contains(boardTest.getCell(1,14)));
		assertTrue(adjDoorUpTest.contains(boardTest.getCell(0,14)));
		
		LinkedList<BoardCell> adjDoorRightTest = boardTest.getAdjList(7,4);
		assertTrue(adjDoorRightTest.contains(boardTest.getCell(7,3)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCell(8,4)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCell(7,5)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCell(6,4)));
		
		LinkedList<BoardCell> adjDoorDownTest = boardTest.getAdjList(11,7);
		assertTrue(adjDoorDownTest.contains(boardTest.getCell(11,6)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCell(12,7)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCell(11,8)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCell(10,7)));
		
		LinkedList<BoardCell> adjDoorLeftTest = boardTest.getAdjList(17,12);
		assertTrue(adjDoorLeftTest.contains(boardTest.getCell(17,11)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCell(18,12)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCell(17,13)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCell(16,12)));
		
		//Locations that are doorways
		LinkedList<BoardCell> doorwayTest1 = boardTest.getAdjList(11,6);
		assertTrue(doorwayTest1.contains(boardTest.getCell(11,7)));
		
		LinkedList<BoardCell> doorwayTest2 = boardTest.getAdjList(18,12);
		assertTrue(doorwayTest2.contains(boardTest.getCell(17,12)));
		
		//Targets along walkways, at various distances
		

	}

}