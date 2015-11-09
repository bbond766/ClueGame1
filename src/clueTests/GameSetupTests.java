package clueTests;

import static org.junit.Assert.*;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Solution;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class GameSetupTests {
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	// The methods that test invalid config files will have a local 
	// Board variable, so will not use this
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Create a new Board using the valid files. Note that
		// the default filenames must be attributes of the Board class. 
		board = new Board();
		// Initialize will load BOTH config files 
		board.initialize();
		board.dealCards();
	}

	@Test
	public void testPeopleConfig() {
		//loadPeopleConfig called in initialize method. 
		//Tests 3 different players for the correct name, color and location
		assertEquals(board.getPlayers().get(0).getName(), "Ms. Peacock");
		assertEquals(board.getPlayers().get(3).getName(), "Rev. Green");
		
		assertEquals(board.getPlayers().get(0).getRow(), 25);
		assertEquals(board.getPlayers().get(3).getRow(), 0);
		
		assertEquals(board.getPlayers().get(0).getColumn(), 0);
		assertEquals(board.getPlayers().get(3).getColumn(), 25);
		
		assertEquals(board.getPlayers().get(0).getColor(), Color.BLUE);
		assertEquals(board.getPlayers().get(3).getColor(), Color.GREEN);
	}
	
	@Test
	public void testCards(){
		//tests the deck for the correct number of each type of card, total cards, and that it contains the correct cards
		assertEquals(board.getCards().size(), 21);
		ArrayList<Card> cards = board.getCards();
		int countWeapons = 0;
		int countPeople = 0;
		int countRooms = 0;
		for (int i = 0; i < cards.size(); i++){
			if (cards.get(i).getType() == CardType.PERSON){
				countPeople++;
			}
			else if (cards.get(i).getType() == CardType.WEAPON){
				countWeapons++;
			}
			else if (cards.get(i).getType() == CardType.ROOM){
				countRooms++;
			}
		}
		assertEquals(countRooms, 9);
		assertEquals(countPeople, 6);
		assertEquals(countWeapons, 6);
		Card knife = new Card("Knife", CardType.WEAPON);
		assertEquals(board.getDeck().get(20).getType(), knife.getType());
		assertEquals(board.getDeck().get(20).getCardName(), knife.getCardName());

		Card ballroom = new Card("Ballroom", CardType.ROOM);
		assertEquals(board.getDeck().get(8).getType(), ballroom.getType());
		assertEquals(board.getDeck().get(8).getCardName(), ballroom.getCardName());

		Card msWhite = new Card("Ms. White", CardType.PERSON);
		assertEquals(board.getDeck().get(1).getType(), msWhite.getType());
		assertEquals(board.getDeck().get(1).getCardName(), msWhite.getCardName());

	}
	
	@Test
	public void testDeal(){
		//tests that the whole deck is dealt
		int totalDealt = 3;

		for (int i = 0; i < board.getPlayers().size(); i++){
			ArrayList<Card> hand = new ArrayList<Card>();
			hand = board.getPlayers().get(i).getCardsInHand();
			totalDealt += board.getPlayers().get(i).getCardsInHand().size();
		}
		assertEquals(totalDealt, 21);
		
		//tests that all players hands are roughly equal
		assertEquals(board.getPlayers().get(0).getCardsInHand().size(), board.getPlayers().get(0).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(0).getCardsInHand().size(), board.getPlayers().get(1).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(1).getCardsInHand().size(), board.getPlayers().get(2).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(2).getCardsInHand().size(), board.getPlayers().get(3).getCardsInHand().size(), 1);
		assertEquals(board.getPlayers().get(3).getCardsInHand().size(), board.getPlayers().get(4).getCardsInHand().size(), 1);
		
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
		for(Integer i =0; i<numPlayersHave.size(); i++){
			assertEquals(numPlayersHave.get(i), (Integer)1);
		}
		
		
	}
	
	@Test 
	public void testAccusation(){
		Solution testAccusation = new Solution(board.getSolution().getPerson(), board.getSolution().getRoom(), board.getSolution().getWeapon());
		assertTrue(board.checkAccusation(testAccusation));
		
		if (testAccusation.getPerson() != "Ms. White"){
			testAccusation.setPerson("Ms. White");
		}
		else {
			testAccusation.setPerson("Ms. Peacock");
		}
		assertFalse(board.checkAccusation(testAccusation));

		if (testAccusation.getRoom() != "Libary"){
			testAccusation.setRoom("Library");
		}
		else {
			testAccusation.setRoom("Observatory");
		}
		assertFalse(board.checkAccusation(testAccusation));
		
		if (testAccusation.getWeapon() != "Lead Pipe"){
			testAccusation.setPerson("Lead Pipe");
		}
		else {
			testAccusation.setPerson("Rope");
		}
		assertFalse(board.checkAccusation(testAccusation));
		}
	}
