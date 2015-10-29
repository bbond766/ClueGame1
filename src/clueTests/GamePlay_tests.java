package clueTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.DoorDirection;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GamePlay_tests {

	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	// The methods that test invalid config files will have a local 
	// Board variable, so will not use this
	private static Board board;
	ArrayList<Player> players;
	Solution solution;
	String solnPerson;
	String solnRoom;
	String solnWeapon;

	@Before
	public void setUp() throws Exception {
		// Create a new Board using the valid files. Note that
		// the default filenames must be attributes of the Board class. 
		board = new Board();
		solution  = new Solution();
		// Initialize will load BOTH config files 
		board.initialize();

//		ArrayList<Card> copyDeck = new ArrayList<Card>(board.getDeck());
//		
//		int index = 0;
//		solnPerson = board.getDeck().get(index).getCardName();
//		copyDeck.remove(index);
//		index = 6;
//		solnRoom = board.getDeck().get(index).getCardName();
//		copyDeck.remove(index-1);
//		index = 15;
//		solnWeapon = board.getDeck().get(index).getCardName();
//		copyDeck.remove(index-2);
//		
//		solution.setPerson(solnPerson);
//		solution.setRoom(solnRoom);
//		solution.setWeapon(solnWeapon);
////		System.out.println(solnPerson + " " + solnRoom + " " + solnWeapon);
//		players =  new ArrayList<Player>(board.getPlayers());
//		
//		while (copyDeck.size() != 0){
//			for (int i = 0; i < players.size(); i++){
//				if (copyDeck.size() == 0){
//					return;
//				}
//				players.get(i).addCardToHand(copyDeck.get(0));
//				copyDeck.remove(0);
//			}
//		}
//		for (int i = 0; i < players.size(); i++){
//			for (int j = 0; j < players.get(i).getCardsInHand().size(); j++){
////				System.out.println("player ith " + i + players.get(i).getCardsInHand().get(j).getCardName() + " j " + j);
//			}
//		}
	}
	
/*	@Test
	public void testDeal(){
		//tests that the whole deck is dealt
		int totalDealt = 3;

		for (int i = 0; i < board.getPlayers().size(); i++){
	//		ArrayList<Card> hand = new ArrayList<Card>();
	//		hand = board.getPlayers().get(i).getCardsInHand();
			totalDealt += board.getPlayers().get(i).getCardsInHand().size();
			System.out.println(totalDealt + "td i " + i);
		}
		assertEquals(totalDealt, 21);
		
		//tests that all players hands are roughly equal
		assertEquals(board.getPlayers().get(0).getCardsInHand().size(), board.getPlayers().get(1).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(1).getCardsInHand().size(), board.getPlayers().get(2).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(2).getCardsInHand().size(), board.getPlayers().get(3).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(3).getCardsInHand().size(), board.getPlayers().get(4).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(4).getCardsInHand().size(), board.getPlayers().get(5).getCardsInHand().size(), 1);
		
		//test if any card is held by more than one player
		ArrayList<Integer> numPlayersHave = new ArrayList<Integer>();
		for(Card x : board.getCards()){
			Integer count = 0;
			Solution solution = new Solution();
			solution = board.getSolution();
			if (solution.getPerson() == x.getCardName()){
				count++;
				numPlayersHave.add(count);
			}
			if (solution.getRoom() == x.getCardName()){
				count++;
				numPlayersHave.add(count);
			}
			if (solution.getWeapon() == x.getCardName()){
				count++;
				numPlayersHave.add(count);
			}
			
			for(int i = 0; i<board.getPlayers().size(); i++){
				if(board.getPlayers().get(i).getCardsInHand().contains(x)){
					count++;
				}
			}		
			numPlayersHave.add(count);
		}
	}

	@Test
	public void testAccusationCorrectness() {
		//solution finalized during deal, which is called by initialize method
		//Solution = Peacock, Conservatory, Lead Pipe
		
		Player player = board.getPlayers().get(0);	//this is a human player
		player.makeAccusation("Ms. Peacock", "Conservatory", "Lead Pipe");
		Solution suggestion = player.getSuggestion();
//		System.out.println(suggestion.getRoom());
		
		assertEquals(solution.getRoom(), suggestion.getRoom());
		assertEquals(solution.getPerson(), suggestion.getPerson());
		assertEquals(solution.getWeapon(), suggestion.getWeapon());
	}
	*/
	//Tests that player returns the one card to disprove the suggestion
	@Test
	public void testDisproveSuggestionOnePossible(){
		ComputerPlayer p1 = new ComputerPlayer("Test1",0,0, Color.BLACK);
		ComputerPlayer p2 = new ComputerPlayer("Test2",0,0, Color.GREEN);
		ArrayList<Player> players = new ArrayList<Player>();
		int row = 0, column = 0;
		ArrayList<Card> cardsNotSeenTest = new ArrayList<Card>();
		BoardCell bc = (board.getCellAt(row, column));
		Card person = new Card("Ms. Scarlet", CardType.PERSON);
		cardsNotSeenTest.add(person);
		Card weapon = new Card("Lead Pipe", CardType.WEAPON);
		cardsNotSeenTest.add(weapon);
		players.add(p1);
		players.add(p2);
		p1.setCardsNotSeen(cardsNotSeenTest);
		p2.addCardToHand(person);
		p1.makeSuggestion(board, bc);
		board.setPlayers(players);
		Card returnCard = new Card();
		returnCard = board.handleSuggestion(p1.getSuggestion(), p1.getName(), bc);
		assertEquals(returnCard.getCardName(), person.getCardName());
		
		
//		Player player = board.getPlayers().get(0);	//this is a human player
//		player.makeAccusation("Ms. Peacock", "Conservatory", "Lead Pipe");
//		Solution suggestion = player.getSuggestion();
////		System.out.println(suggestion.getRoom());
//		Player player2 = board.getPlayers().get(1);
////		System.out.println(player2.getCardsInHand().get(0).getCardName() + "cc");
//		Card card2 = player2.disproveSuggestion(suggestion);
//		System.out.println(player2.getCardsInHand().get(0).getCardName());
//		System.out.println(card2.getCardName() + "card2");
//		assertEquals(solution.getRoom(), suggestion.getRoom());
//		assertEquals(solution.getPerson(), suggestion.getPerson());
//		assertEquals(solution.getWeapon(), suggestion.getWeapon());
//		
//		
//		
//		Player player = players.get(0);	//this is a human player
//		player.makeAccusation("Ms. Scarlet", "Conservatory", "Lead Pipe");	//player 1 should show Scarlet card to disprove
//		Solution suggestion = player.getSuggestion();
////		System.out.println(suggestion.getPerson());
//
//		Player respondingPlayer = players.get(1);
//		Card showCard = new Card("Ms. Scarlet", CardType.PERSON);
//		respondingPlayer.addCardToHand(showCard);
////		System.out.println(respondingPlayer.getCardsInHand().get(0).getCardName());
//		Card cardShown = new Card();
//		cardShown = respondingPlayer.disproveSuggestion(suggestion);	//getCardsInHand().get(0); //
//		System.out.println("NAME + " + cardShown.getCardName());
////		System.out.println("here");
//		
//		for (int i = 1; i < players.size(); i++){
//
//			Player play = board.getPlayers().get(i);
//			if (play.disproveSuggestion(suggestion) != null){
//				System.out.println("Card found.");
//				return;
//			}
//			else{
//				continue;
//			}
//		}
//		Player play = board.getPlayers().get(1);
//		cardShown = play.disproveSuggestion(suggestion);
//		
//		assertEquals(cardShown.getCardName(), "Ms. Scarlet");
//		assertEquals(solution.getPerson(), suggestion.getPerson());
//		assertEquals(solution.getWeapon(), suggestion.getWeapon());
	}
	
	//Tests that player randomly returns one of the cards to disprove the suggestion
	@Test
	public void testDisproveSuggestionTwoPossible(){
		ComputerPlayer p1 = new ComputerPlayer("Test1",0,0, Color.BLACK);
		ComputerPlayer p2 = new ComputerPlayer("Test2",0,0, Color.GREEN);
		ArrayList<Player> players = new ArrayList<Player>();
		int row = 0, column = 0;
		ArrayList<Card> cardsNotSeenTest = new ArrayList<Card>();
		BoardCell bc = (board.getCellAt(row, column));
		Card person = new Card("Ms. Scarlet", CardType.PERSON);
		cardsNotSeenTest.add(person);
		Card weapon = new Card("Lead Pipe", CardType.WEAPON);
		cardsNotSeenTest.add(weapon);
		players.add(p1);
		players.add(p2);
		p1.setCardsNotSeen(cardsNotSeenTest);
		p2.addCardToHand(person);
		p2.addCardToHand(weapon);
		p1.makeSuggestion(board, bc);
		board.setPlayers(players);
		Card returnCard = new Card();
		int personCount = 0, weaponCount = 0;
		for(int i = 0; i<50; i++){
			returnCard = board.handleSuggestion(p1.getSuggestion(), p1.getName(), bc);
			if(returnCard.getCardName() == person.getCardName()){
				personCount++;
			}
			else{
				weaponCount++;
			}
		}
		assertTrue(personCount>10);
		assertTrue(weaponCount>10);
	}
	
	//tests that the players are queried in the correct order
	@Test
	public void testOrder(){
		ArrayList<Player> players = new ArrayList<Player>();
		ComputerPlayer p1 = new ComputerPlayer("Test1",0,0, Color.BLACK);
		ComputerPlayer p2 = new ComputerPlayer("Test2",0,1, Color.BLUE);
		ComputerPlayer p3 = new ComputerPlayer("Test3",1,0, Color.GREEN);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		board.setPlayers(players);
		BoardCell bc = board.getCellAt(0, 0);
		Solution s = new Solution("Ms. Scarlette", "Library", "Rope");
		assertEquals(board.handleSuggestion(s, p1.getName(),bc), null);
		
		assertFalse(p1.isHasBeenQueried());
		assertTrue(p2.isHasBeenQueried());
		assertTrue(p3.isHasBeenQueried());
	}
	
	//Tests that the current player does not attempt to disprove suggestion
	@Test
	public void testAccusingPlayerNoCardReturn(){
		ArrayList<Player> players = new ArrayList<Player>();
		ComputerPlayer p1 = new ComputerPlayer("Test1",0,0, Color.BLACK);
		ComputerPlayer p2 = new ComputerPlayer("Test2",0,1, Color.BLUE);
		ComputerPlayer p3 = new ComputerPlayer("Test3",1,0, Color.GREEN);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		board.setPlayers(players);
		BoardCell bc = board.getCellAt(0, 0);
		Solution s = new Solution("Ms. Scarlette", "Library", "Rope");
		Card card = new Card("Rope", CardType.WEAPON);
		p1.addCardToHand(card);
		assertEquals(board.handleSuggestion(s, p1.getName(),bc), null);
	}
	
	//tests the human player disproving a suggestion
	@Test
	public void testHumanPlayer(){
		ArrayList<Player> players = new ArrayList<Player>();
		HumanPlayer p1 = new HumanPlayer("Test1",0,0, Color.BLACK);
		ComputerPlayer p2 = new ComputerPlayer("Test2",0,1, Color.BLUE);
		ComputerPlayer p3 = new ComputerPlayer("Test3",1,0, Color.GREEN);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		board.setPlayers(players);
		BoardCell bc = board.getCellAt(0, 0);
		Solution s = new Solution("Ms. Scarlette", "Library", "Rope");
		Card card = new Card("Rope", CardType.WEAPON);
		p1.addCardToHand(card);
		assertEquals(board.handleSuggestion(s, p1.getName(),bc), null);
		
		assertFalse(p1.isHasBeenQueried());
		assertTrue(p2.isHasBeenQueried());
		assertTrue(p3.isHasBeenQueried());
	}
	
	//tests that a room is selected if possible
	@Test
	public void testTargetSelectionRoom(){
		//Pass in a list that includes a room, and check that the room is selected. Is it sufficient to call one time? No, the method might randomly choose the room one time. You should put the call in a loop and assert in the body of the loop that the room is chosen every time.
		//set position of player
		//set move
		//public BoardCell(int xPos, int yPos, char initial, DoorDirection direction)
		Color color = Color.RED;
		int row = 4;
		int column = 4;
		String name = "Ms. Scarlet";
		int pathLength = 1;
		
		ComputerPlayer cp = new ComputerPlayer(name, row, column, color);
		BoardCell bc = (board.getCellAt(row, column));
	
		
		board.calcTargets(bc, pathLength);
		Set<BoardCell> targets = board.getTargets();
//		System.out.println(targets.size() + " size");

		cp.pickLocation(targets);
		int chosenRow = cp.getRow();
		int chosenColumn = cp.getColumn();
		BoardCell chosenCell = board.getCellAt(chosenColumn, chosenRow);
//		System.out.println(chosenCell.isDoorway() + "dd isroom " + chosenCell.isRoom());
	     
		//there is one possible square that a player could select to move into that is a room if the player is located on [4][3]
		//verify that the chosenCell, chosen by the pickLocation method, is a room
		assertEquals(chosenCell.getDoorDirection(), DoorDirection.RIGHT);
		assertEquals(chosenCell.getInitial(), 'C');
		assertEquals(chosenCell.getRow(), 3);
		assertEquals(chosenCell.getColumn(), 4);
	    assertTrue(chosenCell.isRoom());
	}
	
	
	//tests that if no room, that a target is selected randomly
	@Test
	public void testTargetSelectionRandom(){
		int row = 7, column = 19;
		ComputerPlayer cp = new ComputerPlayer("test1", row, column, Color.BLACK);
		BoardCell Target1 = board.getCellAt(19, 6);
		BoardCell Target2 = board.getCellAt(18, 7);
		int target1 = 0, target2 = 0;
		//board.calcTargets(bc, 1);
		HashSet<BoardCell> targets = new HashSet<BoardCell>();
		targets.add(Target1);
		targets.add(Target2);
		for(int i = 0; i<100; i++){
			cp.pickLocation(targets);
			int chosenRow = cp.getRow();
			int chosenColumn = cp.getColumn();
			if(chosenColumn == 19||chosenRow == 6){
				target1++;
			}
			else{target2++;}
		}
		System.out.println("target1 " + target1);
		System.out.println("target2 " + target2);
		assertTrue(target1>25);
		assertTrue(target2>25);
	}
	
	//test that takes computer player ignores/avoids a room the player was previously in
	@Test
	public void testTargetSelectionPreviousRoom(){
		Color color = Color.RED;
		int row = 4;
		int column = 3;
		String name = "Ms. Scarlet";
		int pathLength = 6;
		
		ComputerPlayer cp = new ComputerPlayer(name, row, column, color);
		BoardCell bc = (board.getCellAt(row, column));
	
		board.calcTargets(bc, pathLength);
		Set<BoardCell> targets = board.getTargets();
//		System.out.println(targets.size() + " size");

		cp.pickLocation(targets);
		int chosenRow = cp.getRow();
		int chosenColumn = cp.getColumn();
		BoardCell chosenCell = board.getCellAt(chosenColumn, chosenRow);
//		System.out.println(chosenCell.isDoorway() + "dd isroom " + chosenCell.isRoom());
	     
		//there is one possible square that a player could select to move into that is a room if the player is located on [4][3]
		//verify that the chosenCell, chosen by the pickLocation method, is a room
		assertFalse(chosenCell.getDoorDirection() == DoorDirection.RIGHT);
		assertFalse(chosenCell.getInitial() == 'C');
		assertFalse(chosenCell.getRow() == 4);
		assertFalse(chosenCell.getColumn() == 3);
		assertFalse(chosenCell.isRoom());
	}
	
	//tests the computer player making a suggestion with only one possibility
	@Test
	public void testCompPlayerOneSuggestion(){
		int row = 0, column = 0;
		ComputerPlayer cp = new ComputerPlayer("Col. Mustard", 0, 0, Color.BLACK);
		ArrayList<Card> cardsNotSeenTest = new ArrayList<Card>();
		BoardCell bc = (board.getCellAt(row, column));
		Card person = new Card("Ms. Scarlet", CardType.PERSON);
		cardsNotSeenTest.add(person);
		Card weapon = new Card("Lead Pipe", CardType.WEAPON);
		cardsNotSeenTest.add(weapon);
		cp.setCardsNotSeen(cardsNotSeenTest);
		cp.makeSuggestion(board, bc);
		assertEquals(cp.getSuggestion().person, "Ms. Scarlet");
		assertEquals(cp.getSuggestion().weapon, "Lead Pipe");
		assertEquals(cp.getSuggestion().room, "Conservatory");
	}
	
	@Test
	public void testCompPlayerRandPossible(){
		int row = 0, column = 0;
		int numMsScarlette = 0, numProfPlum = 0;
		ComputerPlayer cp = new ComputerPlayer("Col. Mustard", 0, 0, Color.BLACK);
		ArrayList<Card> cardsNotSeenTest = new ArrayList<Card>();
		BoardCell bc = (board.getCellAt(row, column));
		Card person = new Card("Ms. Scarlet", CardType.PERSON);
		cardsNotSeenTest.add(person);
		Card person2 = new Card("Prof. Plum", CardType.PERSON);
		cardsNotSeenTest.add(person2);
		Card weapon = new Card("Lead Pipe", CardType.WEAPON);
		cardsNotSeenTest.add(weapon);
		cp.setCardsNotSeen(cardsNotSeenTest);
		
		for(int i = 0; i<50; i++){
			cp.makeSuggestion(board, bc);
			if(cp.getSuggestion().person == "Ms. Scarlet"){
				numMsScarlette++;
			}
			else{
				numProfPlum++;
			}
		}
		assertEquals(cp.getSuggestion().weapon, "Lead Pipe");
		assertEquals(cp.getSuggestion().room, "Conservatory");
		assertTrue(numMsScarlette>5);
		assertTrue(numProfPlum>5);
	}

}
