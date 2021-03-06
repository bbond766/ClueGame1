package clueTests;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardTests {
	Board boardTest;
	@Before
	public void intBoardSetUp() {
		boardTest = new Board("board.csv","key.txt");
		boardTest.initialize();
		boardTest.calcAdjancencies();
	}

	@Test
	public void boardAdjacencyTests() {
		//Locations with only walkways as adjacent locations
		LinkedList<BoardCell> allWalkways = boardTest.getAdjList(6,17);
		assertTrue(allWalkways.contains(boardTest.getCellAt(6,16)));
		assertTrue(allWalkways.contains(boardTest.getCellAt(7,17)));
		assertTrue(allWalkways.contains(boardTest.getCellAt(6,18)));
		assertTrue(allWalkways.contains(boardTest.getCellAt(5,17)));
		
		//Locations that are at each edge of the board
		LinkedList<BoardCell> topEdgeTest = boardTest.getAdjList(7,0);
		assertTrue(topEdgeTest.contains(boardTest.getCellAt(6,0)));
		assertTrue(topEdgeTest.contains(boardTest.getCellAt(8,0)));
		assertTrue(topEdgeTest.contains(boardTest.getCellAt(7,1)));
		
		LinkedList<BoardCell> rightEdgeTest = boardTest.getAdjList(25,4);
		assertTrue(rightEdgeTest.contains(boardTest.getCellAt(25,3)));
		assertFalse(rightEdgeTest.contains(boardTest.getCellAt(25,5)));
		assertTrue(rightEdgeTest.contains(boardTest.getCellAt(24,4)));
		
		LinkedList<BoardCell> bottomEdgeTest = boardTest.getAdjList(2,25);
		assertTrue(bottomEdgeTest.contains(boardTest.getCellAt(2,24)));
		assertTrue(bottomEdgeTest.contains(boardTest.getCellAt(3,25)));
		assertTrue(bottomEdgeTest.contains(boardTest.getCellAt(1,25)));
		
		LinkedList<BoardCell> leftEdgeTest = boardTest.getAdjList(0,8);
		assertTrue(leftEdgeTest.contains(boardTest.getCellAt(0,7)));
		assertTrue(leftEdgeTest.contains(boardTest.getCellAt(1,8)));
		assertTrue(leftEdgeTest.contains(boardTest.getCellAt(0,9)));
		
		//Locations that are beside a room cell that is not a doorway
		LinkedList<BoardCell> adjRoomTest1 = boardTest.getAdjList(14,4);
		assertTrue(adjRoomTest1.contains(boardTest.getCellAt(14,3)));
		assertTrue(adjRoomTest1.contains(boardTest.getCellAt(15,4)));
		assertTrue(adjRoomTest1.contains(boardTest.getCellAt(14,5)));
		
		LinkedList<BoardCell> adjRoomTest2 = boardTest.getAdjList(21,4);
		assertTrue(adjRoomTest2.contains(boardTest.getCellAt(22,4)));
		assertTrue(adjRoomTest2.contains(boardTest.getCellAt(21,5)));
		
		//Locations that are adjacent to a doorway with needed direction (i.e., the adjacency list will include the doorway)
		LinkedList<BoardCell> adjDoorUpTest = boardTest.getAdjList(1,13);
		assertTrue(adjDoorUpTest.contains(boardTest.getCellAt(1,12)));
		assertTrue(adjDoorUpTest.contains(boardTest.getCellAt(2,13)));
		assertTrue(adjDoorUpTest.contains(boardTest.getCellAt(1,14)));
		assertFalse(adjDoorUpTest.contains(boardTest.getCellAt(0,14)));
		
		LinkedList<BoardCell> adjDoorRightTest = boardTest.getAdjList(7,4);
		assertTrue(adjDoorRightTest.contains(boardTest.getCellAt(7,3)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCellAt(8,4)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCellAt(7,5)));
		assertTrue(adjDoorRightTest.contains(boardTest.getCellAt(6,4)));
		
		LinkedList<BoardCell> adjDoorDownTest = boardTest.getAdjList(11,7);
		assertTrue(adjDoorDownTest.contains(boardTest.getCellAt(11,6)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCellAt(12,7)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCellAt(11,8)));
		assertTrue(adjDoorDownTest.contains(boardTest.getCellAt(10,7)));
		
		LinkedList<BoardCell> adjDoorLeftTest = boardTest.getAdjList(17,12);
		assertTrue(adjDoorLeftTest.contains(boardTest.getCellAt(17,11)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCellAt(18,12)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCellAt(17,13)));
		assertTrue(adjDoorLeftTest.contains(boardTest.getCellAt(16,12)));
		
		//Locations that are doorways
		LinkedList<BoardCell> doorwayTest1 = boardTest.getAdjList(11,6);
		assertTrue(doorwayTest1.contains(boardTest.getCellAt(11,7)));
		
		LinkedList<BoardCell> doorwayTest2 = boardTest.getAdjList(18,12);
		assertTrue(doorwayTest2.contains(boardTest.getCellAt(17,12)));
		
		//Targets along walkways, at various distances
		boardTest.calcTargets(boardTest.getCellAt(6,17), 2);
		Set<BoardCell> targetDistTwoTest = boardTest.getTargets();
		assertTrue(targetDistTwoTest.contains(boardTest.getCellAt(6,15)));
		assertFalse(targetDistTwoTest.contains(boardTest.getCellAt(7,15)));
		assertTrue(targetDistTwoTest.contains(boardTest.getCellAt(7,18)));
		assertTrue(targetDistTwoTest.contains(boardTest.getCellAt(6,19)));
		assertTrue(targetDistTwoTest.contains(boardTest.getCellAt(5,18)));
		assertTrue(targetDistTwoTest.contains(boardTest.getCellAt(5,16)));
		
		boardTest.calcTargets(boardTest.getCellAt(6,17), 3);
		Set<BoardCell> targetDistThreeTest = boardTest.getTargets();
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(5,15)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(7,15)));
		assertFalse(targetDistThreeTest.contains(boardTest.getCellAt(5,16)));
		assertFalse(targetDistThreeTest.contains(boardTest.getCellAt(6,19)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(8,16)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(5,17)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(7,17)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(6,18)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(5,19)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(7,19)));
		assertTrue(targetDistThreeTest.contains(boardTest.getCellAt(6,20)));
		
		boardTest.calcTargets(boardTest.getCellAt(6,17), 4);
		Set<BoardCell> targetDistFourTest = boardTest.getTargets();
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(7,14)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(4,15)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(6,15)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(8,15)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(5,16)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(7,16)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(9,16)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(5,18)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(7,18)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(6,19)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(5,20)));
		assertTrue(targetDistFourTest.contains(boardTest.getCellAt(6,21)));
		assertFalse(targetDistFourTest.contains(boardTest.getCellAt(2,17)));
		
		boardTest.calcTargets(boardTest.getCellAt(16,10), 4);
		Set<BoardCell> targetDistTwoTest2 = boardTest.getTargets();
		assertTrue(targetDistTwoTest2.contains(boardTest.getCellAt(16,8)));
		assertTrue(targetDistTwoTest2.contains(boardTest.getCellAt(17,9)));
		assertTrue(targetDistTwoTest2.contains(boardTest.getCellAt(18,10)));
		assertTrue(targetDistTwoTest2.contains(boardTest.getCellAt(17,11)));
		assertTrue(targetDistTwoTest2.contains(boardTest.getCellAt(16,12)));
		
		//Targets that allow the user to enter a room
		boardTest.calcTargets(boardTest.getCellAt(1,13), 1);
		Set<BoardCell> targetRoomTest = boardTest.getTargets();
		assertTrue(targetRoomTest.contains(boardTest.getCellAt(1,14)));
		
		boardTest.calcTargets(boardTest.getCellAt(16,17), 1);
		Set<BoardCell> targetRoomTest2 = boardTest.getTargets();
		assertFalse(targetRoomTest2.contains(boardTest.getCellAt(15,17)));
		assertFalse(targetRoomTest2.contains(boardTest.getCellAt(16,18)));
		
		//Targets calculated when leaving a room
		boardTest.calcTargets(boardTest.getCellAt(1,14), 1);
		Set<BoardCell> exitRoomTest = boardTest.getTargets();
//		System.out.println(boardTest.getCell(1, 14));
//		System.out.println(boardTest.getCell(1, 13));
		assertTrue(exitRoomTest.contains(boardTest.getCellAt(1,13)));
		assertFalse(exitRoomTest.contains(boardTest.getCellAt(2,14)));
		
		boardTest.calcTargets(boardTest.getCellAt(23,14), 1);
		Set<BoardCell> exitRoomTest2 = boardTest.getTargets();
		assertTrue(exitRoomTest2.contains(boardTest.getCellAt(23,15)));
		assertFalse(exitRoomTest2.contains(boardTest.getCellAt(22,14)));
	}

}
