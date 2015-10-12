import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IntBoard {
	private int numRows,numColumns;
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	final static int BOARD_SIZE = 26;
	BoardCell[][] gameBoard = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	public Map<BoardCell, LinkedList<BoardCell>> adjacencyList = new HashMap<BoardCell, LinkedList<BoardCell>>(); 
	private Set<BoardCell> targets;
	private static Map<Character, String> rooms;
	private String boardConfigFile;
	private String roomConfigFile;
	IntBoard(String boardConfigFile, String roomConfigFile)
	{
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}
	public void initialize()
	{
		try {
			loadBoardConfigFile(boardConfigFile);
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadRoomConfigFile(roomConfigFile);
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadRoomConfigFile(String roomConfigFile) throws BadConfigFormatException
	{
		FileReader reader;
		try {
			reader = new FileReader(roomConfigFile);
			Scanner in = new Scanner(reader);
			while(in.hasNextLine())
			{
				String a = in.nextLine();
				String[] test = a.split(", ");
				if(test.length != 3)
				{
					throw new BadConfigFormatException("Improper format for key, Incorrect number of elements found on a line");
				}
				if(test[2].equals("Card"))
					rooms.put(test[0].charAt(0), test[1]);
				if(!test[2].equals("Card") || !test[2].equals("Other"))
				{
					throw new BadConfigFormatException("Improper type for a room");
				}
			}
		} catch (FileNotFoundException e) {
			throw new BadConfigFormatException(e.getMessage());
		}
		
		
		
	}
	public void loadBoardConfigFile(String filename)throws BadConfigFormatException
	{
		try {
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);
			in.useDelimiter("\\s,\\s|\\s\n\\s");
			int i = 0;
			while(in.hasNext())
			{
				
				String a = new String(in.next());
				char initial = a.charAt(0);
				if(a.charAt(0) >47 && a.charAt(0) < 58){
					continue;
				}
				if(i > BOARD_SIZE*BOARD_SIZE)
				{
					throw new BadConfigFormatException("File length excedded expected board size.");
				}
				if(a.length()>1)
				{
					if(a.charAt(1) != 'N')
					{
						gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.NONE);
					}
					else
					{
						switch(a.charAt(1))
						{
						case 'U':
							gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.UP);
							break;
						case 'D':
							gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.DOWN);
							break;
						case 'L':
							gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.LEFT);
							break;
						case 'R':
							gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.RIGHT);
							break;
						}
					}
						
				}
				else
				{
					gameBoard[i%numColumns][i/numColumns] = new BoardCell(i%numColumns,i/numColumns, initial, DoorDirection.NONE);
				}
				
				i++;
			}
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
	}
	
	public static Map<Character, String> getRooms() {
		return rooms;
	}
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
