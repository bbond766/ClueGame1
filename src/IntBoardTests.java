import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	IntBoard boardTest;
	@Before
	public void intBoardSetUp() {
		boardTest = new IntBoard();
		boardTest.calcAdjancencies();
	} 
	
	@Test
	public void adjacencyTest() {
		LinkedList<BoardCell> topLeftTest = boardTest.getAdjList(0,0);
		assertTrue(topLeftTest.contains(boardTest.getCell(1,0)));
		assertTrue(topLeftTest.contains(boardTest.getCell(0,1)));
//		System.out.println(boardTest.getAdjList(0,0));
		
		
		LinkedList<BoardCell> bottomRightTest = boardTest.getAdjList(3,3);
		assertTrue(bottomRightTest.contains(boardTest.getCell(3,2)));
		assertTrue(bottomRightTest.contains(boardTest.getCell(2,3)));
		
		LinkedList<BoardCell> rightSideTest = boardTest.getAdjList(1,3);
		assertTrue(rightSideTest.contains(boardTest.getCell(1,2)));
		assertTrue(rightSideTest.contains(boardTest.getCell(2,3)));
		assertTrue(rightSideTest.contains(boardTest.getCell(0,3)));
		
		LinkedList<BoardCell> leftSideTest = boardTest.getAdjList(3,0);
		assertTrue(leftSideTest.contains(boardTest.getCell(3,1)));
		assertTrue(leftSideTest.contains(boardTest.getCell(2,0)));
		
		LinkedList<BoardCell> leftMiddleTest = boardTest.getAdjList(1,1);
		assertTrue(leftMiddleTest.contains(boardTest.getCell(1,0)));
		assertTrue(leftMiddleTest.contains(boardTest.getCell(2,1)));
		assertTrue(leftMiddleTest.contains(boardTest.getCell(1,2)));
		assertTrue(leftMiddleTest.contains(boardTest.getCell(0,1)));
		
		LinkedList<BoardCell> rightMiddleTest = boardTest.getAdjList(2,2);
		assertTrue(rightMiddleTest.contains(boardTest.getCell(2,1)));
		assertTrue(rightMiddleTest.contains(boardTest.getCell(3,2)));
		assertTrue(rightMiddleTest.contains(boardTest.getCell(2,3)));
		assertTrue(rightMiddleTest.contains(boardTest.getCell(1,2)));
		
	}
	
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = boardTest.getCell(0, 0);
		boardTest.calcTargets(cell, 3);
		Set targets = boardTest.getTargets();
		System.out.println(targets);
		assertEquals(6, targets.size());
		assertTrue(targets.contains(boardTest.getCell(3, 0)));
		assertTrue(targets.contains(boardTest.getCell(2, 1)));
		assertTrue(targets.contains(boardTest.getCell(0, 1)));
		assertTrue(targets.contains(boardTest.getCell(1, 2)));
		assertTrue(targets.contains(boardTest.getCell(0, 3)));
		assertTrue(targets.contains(boardTest.getCell(1, 0)));
	}	

}
