import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	final static int BOARD_SIZE = 4;
	BoardCell[][] gameBoard = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	public Map<BoardCell, LinkedList<BoardCell>> adjacencyList = new HashMap<BoardCell, LinkedList<BoardCell>>(); 
	private Set<BoardCell> targets;
	private static Map<Character, String> rooms;
	
	public void calcAdjancencies()
	{
		
		for(int i = 0; i < gameBoard.length;i++)
		{
			for(int j = 0; j < gameBoard[i].length;j++)
			{
				LinkedList<BoardCell> temp= new LinkedList<BoardCell>();
				if(j+1 <BOARD_SIZE)
				{
					temp.add(gameBoard[i][j+1]);
				}
				if(i+1 < BOARD_SIZE)
				{
					temp.add(gameBoard[i+1][j]);
				}
				if(j-1 >=0)
				{
					temp.add(gameBoard[i][j-1]);
				}
				if(i-1 >=0)
				{
					temp.add(gameBoard[i-1][j]);
				}
				
				adjacencyList.put(gameBoard[i][j],temp);
				
			}
		}
		
		
	}
	public IntBoard() {
		super();
		for(int i = 0; i < gameBoard.length;i++)
		{
			for(int j = 0; j < gameBoard[i].length;j++)
			{
				gameBoard[i][j] = new BoardCell(i,j); 
				
			}
		}
	}
	public BoardCell getCell(int xPos, int yPos)
	{
		return gameBoard[xPos][yPos];
	}
	public void calcTargets(BoardCell startCell, int pathLength){
		targets = drawLine(new HashSet<BoardCell>(),startCell, pathLength);
		
	}
	public Set<BoardCell> getTargets()
	{
		return targets;
	}
	
	private Set<BoardCell> drawLine(Set<BoardCell> usedSpaces, BoardCell currentLocation, int spacesLeft)
	{
		usedSpaces.add(currentLocation);
		Set<BoardCell> targetsList = new HashSet<BoardCell>();
		if(spacesLeft==0)
		{
			targetsList.add(currentLocation);
			usedSpaces.remove(currentLocation);
			return targetsList;
		}
		for(BoardCell i : getAdjList(currentLocation.xPos, currentLocation.yPos))
		{
			if(!usedSpaces.contains(i)){
				Set<BoardCell> temp = drawLine(usedSpaces, i, spacesLeft-1);
				for(BoardCell j : temp)
					targetsList.add(j);
			}
		}
		usedSpaces.remove(currentLocation);
		return targetsList;
	}
	public LinkedList<BoardCell>getAdjList(int xPos, int yPos)
	{
		LinkedList<BoardCell> temp = new LinkedList<BoardCell>(adjacencyList.get(getCell(xPos,yPos)));
		return temp;
	}
}
