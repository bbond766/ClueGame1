package clueGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Board extends JPanel{
	private int numRows,numColumns;
	protected int rowPixel, colPixel;
	
	public final static int BOARD_SIZE = 26;
	public final static int PIXEL_BOARD_SIZE = BOARD_SIZE*20;
	public final static int PLAYER_NUM = 6;
	public final static int DECK_SIZE = 21;
	public final static int NUM_PEOPLE_CARDS = 6;
	public final static int NUM_ROOM_CARDS = 9;
	public final static int NUM_WEAPON_CARDS = 6;
	public static final int DIMENSION_X = Board.PIXEL_BOARD_SIZE/26;
	public static final int DIMENSION_Y = Board.PIXEL_BOARD_SIZE/26;
	
	static BoardCell[][] gameBoard = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	public Map<BoardCell, LinkedList<BoardCell>> adjacencyList = new HashMap<BoardCell, LinkedList<BoardCell>>(); 
	private Set<BoardCell> targets;
	private static Map<Character, String> rooms;
	private static ArrayList<Player> players;
	private ArrayList<Card> deck;
	private Solution solution;
	private String boardConfigFile = "board.csv";
	private String roomConfigFile = "ClueLegend.txt";
	private String playerConfigFile = "playerLoad.csv";
	private String cardDeckFile = "cardDeckFile.csv";
	public Board()
	{
		super();
		rooms = new HashMap<Character,String>();
		players = new ArrayList<Player>();
		deck = new ArrayList<Card>();
		solution = new Solution();
	}
	public Board(String boardConfigFile, String roomConfigFile)
	{
		super();
		this.players = new ArrayList<Player>();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		rooms = new HashMap<Character,String>();
		deck = new ArrayList<Card>();
		solution = new Solution();
	}
	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public void initialize()
	{
		try {
			loadRoomConfig();
			loadBoardConfig();
			loadPlayerConfig();
			calcAdjancencies();
			loadCards();
//			dealCards();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		this.rowPixel = 0;
		this.colPixel = 0;
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
						else if(test[j].charAt(1) == 'T'){
							gameBoard[i][j] = new BoardCell(i,j, initial, DoorDirection.NONE);
							gameBoard[i][j].setDisplayName(true);
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
				i++;
			}
			numRows=i;
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
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
				if(j+1 < numColumns )
				{
					if(((gameBoard[i][j].initial == gameBoard[i][j+1].initial)&& !gameBoard[i][j].isRoom()) ||(gameBoard[i][j].getDoorDirection().equals(DoorDirection.RIGHT) ||gameBoard[i][j+1].getDoorDirection().equals(DoorDirection.LEFT)))
						temp.add(gameBoard[i][j+1]);
					
				}
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
		if(spacesLeft==0 || currentLocation.isDoorway())
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
			boolean humanPlayerAssigned = false;
			while(in.hasNextLine())
			{
				String a = in.nextLine();
				String[] test = a.split(",");
				if( test.length != 4)
					throw new BadConfigFormatException("Player config file is not formatted correctly.");
					String playerName = test[0];
					String color = test[1];
					color = color.toUpperCase();
					Color colorPlayer = null;
					
					switch (color){
					case "BLUE":
						colorPlayer = Color.BLUE;
						break;
					case "WHITE":
						colorPlayer = Color.WHITE;
						break;
					case "RED":
						colorPlayer = Color.RED;
						break;
					case "YELLOW":
						colorPlayer = Color.YELLOW;
						break;
					case "GREEN":
						colorPlayer = Color.GREEN;
						break;
					case "PURPLE":
						colorPlayer = Color.MAGENTA;
						break;
					default:
						System.out.println("Color not found.");
					}
					int column = Integer.parseInt(test[2]);
					int row = Integer.parseInt(test[3]);
					//human player always at index 0; can randomize; set in this way so can use in tests
					if (!humanPlayerAssigned){
						HumanPlayer next = new HumanPlayer(playerName, row, column, colorPlayer);
						players.add(next);
						humanPlayerAssigned = true;
					}
					else{
						ComputerPlayer next = new ComputerPlayer(playerName, row, column, colorPlayer);
						players.add(next);
					}
					
			}
			if (players.size() != PLAYER_NUM){
				throw new BadConfigFormatException("The number of players read in from file is incorrect.");
			}
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
	}
	
	public void loadCards() throws BadConfigFormatException {
		//cardDeckFile.csv
		try {
			FileReader reader = new FileReader(cardDeckFile);
			Scanner in = new Scanner(reader);
			int i = 0;
			while(in.hasNextLine())
			{
				CardType cardType = null;
				String a = in.nextLine();
				String[] test = a.split(",");
				if( test.length != 2)
					throw new BadConfigFormatException("Player config file is not formatted correctly.");
					String cardName = test[0];
					String type = test[1];
					
					switch (type){
					case "PERSON":
						cardType = CardType.PERSON;
						break;
					case "ROOM":
						cardType = CardType.ROOM;
						break;
					case "WEAPON":
						cardType = CardType.WEAPON;
						break;
					default:
						System.out.println("Type not found.");
					}
					Card next = new Card(cardName, cardType);
					deck.add(next);
			}
			if (deck.size() != DECK_SIZE){
				throw new BadConfigFormatException("The number of cards read in from file is incorrect; deck size = " + deck.size());
			}
		} catch (FileNotFoundException e) {
			BadConfigFormatException ex = new BadConfigFormatException(e.getMessage());
			throw ex;
		}
	}
	public void dealCards(){
		ArrayList<Card> copyDeck = new ArrayList<Card>(deck);
		String solnPerson;
		String solnRoom;
		String solnWeapon;
	
		//rand card from index 0 to NUM_PEOPLE_CARDS - 1
		int index = (int)(Math.random() % NUM_PEOPLE_CARDS);
		solnPerson = copyDeck.get(index).getCardName();
		copyDeck.remove(index);
		
		//rand card from index NUM_PEOPLE_CARDS to (NUM_PEOPLE_CARDS + (NUM_ROOM_CARDS - 1))
		index = (int)(Math.random() % NUM_ROOM_CARDS) + (NUM_PEOPLE_CARDS);
		solnRoom = copyDeck.get(index).getCardName();
		copyDeck.remove(index);
		
		//rand card from index (NUM_PEOPLE_CARDS + (NUM_ROOM_CARDS)) to DECK_SIZE - 1
		index = (int)(Math.random() % NUM_WEAPON_CARDS) + (NUM_ROOM_CARDS + NUM_PEOPLE_CARDS);
		solnWeapon = copyDeck.get(index).getCardName();
		copyDeck.remove(index);
		
		solution.setPerson(solnPerson);
		solution.setRoom(solnRoom);
		solution.setWeapon(solnWeapon);
		
	
		while (copyDeck.size() != 0){
			for (int i = 0; i < players.size(); i++){
				if (copyDeck.size() == 0){
					return;
				}
				players.get(i).addCardToHand(copyDeck.get(0));
				copyDeck.remove(0);
			}
		}
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
	public void movePlayer(int index){
		int moveLength = (int) Math.floor(Math.random()* 6 + 1);
		BoardCell bc = getCellAt(players.get(index).getColumn(), players.get(index).getRow());
		calcTargets(bc, moveLength); //targets updated
		if(players.get(index).getPlayerType()){
			//comp player
			players.get(index).pickLocation(targets);
			players.get(index).setRoomIn(bc.getInitial());
	//		players.makeSuggestion();
			
		}
	}
	public void selectAnswer(){
		
	}
	public Card handleSuggestion(Solution suggestion, String accusingPlayer, BoardCell clicked){
		int indexAP = players.indexOf(accusingPlayer);
		Card cardToShow = new Card();
		indexAP+=2;
		for (int i = 0; i < (players.size() - 1); i++){
			indexAP+=i;
			if (indexAP < players.size()){
				cardToShow = players.get(indexAP).disproveSuggestion(suggestion);
				players.get(indexAP).setHasBeenQueried(true);
				if (cardToShow != null){
					return cardToShow;
				}
			}
			else{
				indexAP = 0;
				cardToShow = players.get(indexAP).disproveSuggestion(suggestion);
				players.get(indexAP).setHasBeenQueried(true);
				if (cardToShow != null){
					return cardToShow;
				}
			}
		}
		//		if (cardToShow == null){
		System.out.println("No players have cards to show to disprove this accusation.");
		return null;
		//		}
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public boolean checkAccusation(Solution accusation){
		if (accusation.getPerson() == solution.getPerson() && accusation.getRoom() == solution.getRoom() && accusation.getWeapon() == solution.getWeapon())
			return true;
		else
			return false;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		g.setColor(Color.gray);
//		g.fillRect(0, 0, 26*26, 26*26);
		for(int i = 0; i < Board.BOARD_SIZE; i++){
			for(int j = 0; j<Board.BOARD_SIZE; j++){
				getCellAt(i,j).draw(g);
			}
		}
	}
	
	//ONLY INTENDED FOR TESTING
	public Solution getSolution(){
		return this.solution;
	}
	
	public static void main(String [] args){
	}
	
}
//pick fn in player class called AFTER player is moved in board class; player's dice roll and calculation of targets
//calculated here 
