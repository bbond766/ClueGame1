package clueTests;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class IntBoardTests {
	Board boardTest;
	@Before
	public void intBoardSetUp() {
		boardTest = new Board("","");
		boardTest.calcAdjancencies();
	} 
	
	@Test
	public void adjacencyTest() {
		LinkedList<BoardCell> topLeftTest = boardTest.getAdjList(0,0);
		assertTrue(topLeftTest.contains(boardTest.getCellAt(1,0)));
		assertTrue(topLeftTest.contains(boardTest.getCellAt(0,1)));
//		System.out.println(boardTest.getAdjList(0,0));
		
		
		LinkedList<BoardCell> bottomRightTest = boardTest.getAdjList(3,3);
		assertTrue(bottomRightTest.contains(boardTest.getCellAt(3,2)));
		assertTrue(bottomRightTest.contains(boardTest.getCellAt(2,3)));
		
		LinkedList<BoardCell> rightSideTest = boardTest.getAdjList(1,3);
		assertTrue(rightSideTest.contains(boardTest.getCellAt(1,2)));
		assertTrue(rightSideTest.contains(boardTest.getCellAt(2,3)));
		assertTrue(rightSideTest.contains(boardTest.getCellAt(0,3)));
		
		LinkedList<BoardCell> leftSideTest = boardTest.getAdjList(3,0);
		assertTrue(leftSideTest.contains(boardTest.getCellAt(3,1)));
		assertTrue(leftSideTest.contains(boardTest.getCellAt(2,0)));
		
		LinkedList<BoardCell> leftMiddleTest = boardTest.getAdjList(1,1);
		assertTrue(leftMiddleTest.contains(boardTest.getCellAt(1,0)));
		assertTrue(leftMiddleTest.contains(boardTest.getCellAt(2,1)));
		assertTrue(leftMiddleTest.contains(boardTest.getCellAt(1,2)));
		assertTrue(leftMiddleTest.contains(boardTest.getCellAt(0,1)));
		
		LinkedList<BoardCell> rightMiddleTest = boardTest.getAdjList(2,2);
		assertTrue(rightMiddleTest.contains(boardTest.getCellAt(2,1)));
		assertTrue(rightMiddleTest.contains(boardTest.getCellAt(3,2)));
		assertTrue(rightMiddleTest.contains(boardTest.getCellAt(2,3)));
		assertTrue(rightMiddleTest.contains(boardTest.getCellAt(1,2)));
		
	}
	
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = boardTest.getCellAt(0, 0);
		boardTest.calcTargets(cell, 3);
		Set targets = boardTest.getTargets();
		System.out.println(targets);
		assertEquals(6, targets.size());
		assertTrue(targets.contains(boardTest.getCellAt(3, 0)));
		assertTrue(targets.contains(boardTest.getCellAt(2, 1)));
		assertTrue(targets.contains(boardTest.getCellAt(0, 1)));
		assertTrue(targets.contains(boardTest.getCellAt(1, 2)));
		assertTrue(targets.contains(boardTest.getCellAt(0, 3)));
		assertTrue(targets.contains(boardTest.getCellAt(1, 0)));
	}	

}
