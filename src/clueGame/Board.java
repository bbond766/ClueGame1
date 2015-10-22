package clueGame;
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

public class Board {
	private int numRows,numColumns;
	
	public final static int BOARD_SIZE = 100;
	public final static int PLAYER_NUM = 6;
	BoardCell[][] gameBoard = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	public Map<BoardCell, LinkedList<BoardCell>> adjacencyList = new HashMap<BoardCell, LinkedList<BoardCell>>(); 
	private Set<BoardCell> targets;
	private static Map<Character, String> rooms;
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private String boardConfigFile = "ClueLayout.csv";
	private String roomConfigFile = "ClueLegend.txt";
	private String playerConfigFile = "playerLoad.csv";
	private String cardDeckFile = "cardDeck.csv";
	public Board()
	{
		super();
		rooms = new HashMap<Character,String>();
	}
	public Board(String boardConfigFile, String roomConfigFile)
	{
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		rooms = new HashMap<Character,String>();
	}
	public void initialize()
	{
		try {
			loadRoomConfig();
			loadBoardConfig();
			loadPlayerConfig();
			calcAdjancencies();
			loadCards();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
	}
	public void loadRoomConfig() throws BadConfigFormatException
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
				rooms.put(test[0].charAt(0), test[1]);
				if(!test[2].equals("Card") && !test[2].equals("Other"))
				{
					throw new BadConfigFormatException("Improper type for a room");
				}
			}
		} catch (FileNotFoundException e) {
			throw new BadConfigFormatException(e.getMessage());
		}
		
		
		
	}
	public void loadBoardConfig()throws BadConfigFormatException
	{
		try {
			FileReader reader = new FileReader(boardConfigFile);
			Scanner in = new Scanner(reader);
			int i = 0;
			
			while(in.hasNextLine())
			{
				String a = in.nextLine();
				String[] test = a.split(",");
				if(i==0)
					numColumns=test.length;
				if( test.length != numColumns)
					throw new BadConfigFormatException("File length excedded max board size.");
				for(int j = 0; j < test.length;j++)
				{
//					System.out.print('['+test[j]+','+j+','+i+"],");
					char initial = test[j].charAt(0);
					if(!rooms.containsKey(initial))
					{
						throw new BadConfigFormatException("Room initial did not match any values in the legend");
					}
					if(test[j].length()>1)
					{
						if(test[j].charAt(1) == 'N')
						{
							gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.NONE);
						}
						else
						{
							switch(test[j].charAt(1))
							{
							case 'U':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.UP);
								break;
							case 'D':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.DOWN);
								break;
							case 'L':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.LEFT);
								break;
							case 'R':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.RIGHT);
								break;
							}
						}
							
					}
					else
					{
						gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.NONE);
					}
				}
				//System.out.println();
				i++;
//				System.out.println(i);
			}
			numRows=i;
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
		//System.out.println();
		//System.out.println();
	}
	
	public static Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public void calcAdjancencies()
	{
		
		for(int i = 0; i < numRows;i++)
		{
			for(int j = 0; j < numColumns;j++)
			{
				LinkedList<BoardCell> temp= new LinkedList<BoardCell>();
				
				if(gameBoard[i][j].isRoom() && !gameBoard[i][j].isDoorway()){
					adjacencyList.put(gameBoard[i][j],temp);
					continue;
				}
//				System.out.println("["+i+","+j+","+gameBoard[i][j].initial+"]");
				if(j+1 < numColumns )
				{
//					System.out.println("inside");
					if(((gameBoard[i][j].initial == gameBoard[i][j+1].initial)&& !gameBoard[i][j].isRoom()) ||(gameBoard[i][j].getDoorDirection().equals(DoorDirection.RIGHT) ||gameBoard[i][j+1].getDoorDirection().equals(DoorDirection.LEFT)))
						temp.add(gameBoard[i][j+1]);
					
				}
//				System.out.println(i+","+j+","+numColumns);
				if(i+1 < numRows)
				{
					if(((gameBoard[i][j].initial == gameBoard[i+1][j].initial)&& !gameBoard[i][j].isRoom())||(gameBoard[i][j].getDoorDirection().equals(DoorDirection.DOWN) ||gameBoard[i+1][j].getDoorDirection().equals(DoorDirection.UP)))
						temp.add(gameBoard[i+1][j]);
				}
				if(j-1 >=0)
				{
					if(((gameBoard[i][j].initial == gameBoard[i][j-1].initial)&& !gameBoard[i][j].isRoom()) || (gameBoard[i][j].getDoorDirection().equals(DoorDirection.LEFT) || (gameBoard[i][j-1].getDoorDirection().equals(DoorDirection.RIGHT))))
						{temp.add(gameBoard[i][j-1]);}
				}
				if(i-1 >=0)
				{
					if(((gameBoard[i][j].initial == gameBoard[i-1][j].initial)&& !gameBoard[i][j].isRoom()) || (gameBoard[i][j].getDoorDirection().equals(DoorDirection.UP) || (gameBoard[i-1][j].getDoorDirection().equals(DoorDirection.DOWN))))
						temp.add(gameBoard[i-1][j]);
				}
				
				adjacencyList.put(gameBoard[i][j],temp);
				
			}
		}
		
		
	}
	
	public BoardCell getCellAt(int xPos, int yPos)
	{
		return gameBoard[xPos][yPos];
	}
	public void calcTargets(BoardCell startCell, int pathLength){
		if(startCell.isDoorway())
		{
			targets = new HashSet<BoardCell>();
			/*Line above is only used when the start cell is a non doorway inside of a room, 
				in current gameplay this would never happen but some tests need this line, 
			*/
//			System.out.println(startCell);
			Set<BoardCell> usedSpaces = new HashSet<BoardCell>();
			usedSpaces.add(startCell);
			for(BoardCell i: adjacencyList.get(startCell))
			{
				if(!i.isRoom())//TODO: Remove condition to allow movement through a room
				{
					targets = drawLine(usedSpaces,i, pathLength-1);
				}
			}
		}
		else
		{
			targets = drawLine(new HashSet<BoardCell>(),startCell, pathLength);
		}
		
		
	}
	public Set<BoardCell> getTargets()
	{
		return targets;
	}
	
	private Set<BoardCell> drawLine(Set<BoardCell> usedSpaces, BoardCell currentLocation, int spacesLeft)
	{
		usedSpaces.add(currentLocation);
		Set<BoardCell> targetsList = new HashSet<BoardCell>();
//		System.out.println(currentLocation+","+currentLocation.isDoorway());
		//System.out.println(usedSpaces+","+currentLocation+","+spacesLeft);
		if(spacesLeft==0 || currentLocation.isDoorway())
		{
			targetsList.add(currentLocation);
			usedSpaces.remove(currentLocation);
//			System.out.println(currentLocation);
			return targetsList;
		}
//		System.out.println("something");
//		System.out.println(currentLocation.xPos+","+ currentLocation.yPos);
//		System.out.println(getAdjList(currentLocation.xPos, currentLocation.yPos));
		for(BoardCell i : getAdjList(currentLocation.xPos, currentLocation.yPos))
		{
			
			//System.out.println(i);
			if(!usedSpaces.contains(i)){
				Set<BoardCell> temp = drawLine(usedSpaces, i, spacesLeft-1);
				for(BoardCell j : temp)
					if(!j.isRoom() || j.isDoorway())
						targetsList.add(j);
			}
		}
		usedSpaces.remove(currentLocation);
		return targetsList;
	}
	public LinkedList<BoardCell>getAdjList(int xPos, int yPos)
	{
		LinkedList<BoardCell> temp = new LinkedList<BoardCell>(adjacencyList.get(getCellAt(xPos,yPos)));
		return temp;
	}
	public void loadPlayerConfig() throws BadConfigFormatException {
		try {
			FileReader reader = new FileReader(playerConfigFile);
			Scanner in = new Scanner(reader);
			int i = 0;
			
			while(in.hasNextLine())
			{
				String a = in.nextLine();
				String[] test = a.split(",");
//				if(i==0)
//					numColumns=test.length;
				if( test.length != 4)
					throw new BadConfigFormatException("Player config file is not formatted correctly.");
//				for(int j = 0; j < test.length;j++)
//				{
//					System.out.print('['+test[j]+','+j+','+i+"],");
					System.out.println("Test length (player file) = " + test.length);
					String playerName = test[0];
					String color = test[1];
					int column = Integer.parseInt(test[2]);
					int row = Integer.parseInt(test[3]);
					
					
					
					if(!rooms.containsKey(initial))
					{
						throw new BadConfigFormatException("Room initial did not match any values in the legend");
					}
					if(test[j].length()>1)
					{
						if(test[j].charAt(1) == 'N')
						{
							gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.NONE);
						}
						else
						{
							switch(test[j].charAt(1))
							{
							case 'U':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.UP);
								break;
							case 'D':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.DOWN);
								break;
							case 'L':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.LEFT);
								break;
							case 'R':
								gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.RIGHT);
								break;
							}
						}
							
					}
					else
					{
						gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.NONE);
					}
				}
				//System.out.println();
				i++;
//				System.out.println(i);
			}
			numRows=i;
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
	}
	
	public void loadCards() throws BadConfigFormatException {
		//cardDeckFile.csv
	}
	public ArrayList<Card> getCards(){
		return deck;
	}
	public void calcTargets(int i, int j, int k) {
		calcTargets(getCellAt(i,j),k);
		
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}

	public void selectAnswer(){
		
	}
	public Card handleSuggestion(Solution suggestion, String accusingPlayer, BoardCell clicked){
		return null;
	}
	public boolean checkAccusation(Solution accusation){
		return false;
	}
}
