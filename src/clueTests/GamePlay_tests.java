package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
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
	ArrayList<Player> players = new ArrayList<Player>(board.getPlayers());
	Solution solution = new Solution();
	String solnPerson;
	String solnRoom;
	String solnWeapon;

	@Before
	public void setUp() throws Exception {
		// Create a new Board using the valid files. Note that
		// the default filenames must be attributes of the Board class. 
		board = new Board();

		// Initialize will load BOTH config files 
		board.initialize();
//		board.loadCards();
//		System.out.println("deck size " + board.getDeck().size());
//
//		ArrayList<Card> copyDeck = new ArrayList<Card>();
//		
//		int index = 0;
//		solnPerson = copyDeck.get(index).getCardName();
////		copyDeck.remove(index);
//		
//		index = 6;
//		solnRoom = copyDeck.get(index).getCardName();
////		copyDeck.remove(index);
//		
//		
//		index = 15;
//		solnWeapon = copyDeck.get(index).getCardName();
////		copyDeck.remove(index);
//		
//		solution.setPerson(solnPerson);
//		solution.setRoom(solnRoom);
//		solution.setWeapon(solnWeapon);
//		
//		int index2 = 0;
//		for (int i = 0; i < 3; i++)){
//			for (int j = 0; i < players.size(); i++){	//p=6
//				if (i == 0 && i == 6 && i == 15){
//					index2++;
//					continue;
//				}
//				players.get(i).addCardToHand(board.getDeck().get(index2++));
//				index2++;
//			}
//		}
	}
	
//	@Test
//	public void testDeal(){
//		//tests that the whole deck is dealt
//		int totalDealt = 3;
//
//		for (int i = 0; i < board.getPlayers().size(); i++){
//			ArrayList<Card> hand = new ArrayList<Card>();
//			hand = board.getPlayers().get(i).getCardsInHand();
//			totalDealt += board.getPlayers().get(i).getCardsInHand().size();
//			System.out.println(totalDealt + "td i " + i);
//		}
//		assertEquals(totalDealt, 21);
//		
//		//tests that all players hands are roughly equal
//		assertEquals(board.getPlayers().get(0).getCardsInHand().size(), board.getPlayers().get(1).getCardsInHand().size(), 1);
//		assertEquals(board.getPlayers().get(1).getCardsInHand().size(), board.getPlayers().get(2).getCardsInHand().size(), 1);
//		assertEquals(board.getPlayers().get(2).getCardsInHand().size(), board.getPlayers().get(3).getCardsInHand().size(), 1);
//		assertEquals(board.getPlayers().get(3).getCardsInHand().size(), board.getPlayers().get(4).getCardsInHand().size(), 1);
//		assertEquals(board.getPlayers().get(4).getCardsInHand().size(), board.getPlayers().get(5).getCardsInHand().size(), 1);
//		
//		//test if any card is held by more than one player
//		ArrayList<Integer> numPlayersHave = new ArrayList<Integer>();
//		for(Card x : board.getCards()){
//			Integer count = 0;
//			Solution solution = new Solution();
//			solution = board.getSolution();
//			if (solution.getPerson() == x.getCardName()){
//				count++;
//				numPlayersHave.add(count);
//			}
//			if (solution.getRoom() == x.getCardName()){
//				count++;
//				numPlayersHave.add(count);
//			}
//			if (solution.getWeapon() == x.getCardName()){
//				count++;
//				numPlayersHave.add(count);
//			}
//			
//			for(int i = 0; i<board.getPlayers().size(); i++){
//				if(board.getPlayers().get(i).getCardsInHand().contains(x)){
//					count++;
//				}
//			}		
//			numPlayersHave.add(count);
//		}
//		for(Integer i =0; i<numPlayersHave.size(); i++){
//			assertEquals(numPlayersHave.get(i), (Integer)1);
//		}
//		
//		
//	}
//	
//	@Test
//	public void testAccusationCorrectness() {
//		//solution finalized during deal, which is called by initialize method
//		Solution solution = new Solution("Ms. White", "Library", "Lead Pipe");
//		
//		Player player = board.getPlayers().get(0);
//		player.makeSuggestion(board, board.getCellAt(player.getColumn(), player.getRow()));
//		Solution suggestion = player.getSuggestion();
//		
//		if (board.getCellAt(player.getColumn(), player.getRow()).isRoom()){
//			char i = board.getCellAt(player.getColumn(), player.getRow()).getInitial();
//			String val = board.getRooms().get(i);
//			assertEquals(val, suggestion.getRoom());
//			assertTrue(suggestion.getPerson() == solution.getPerson());
//			assertTrue(suggestion.getRoom() == solution.getRoom());
//			assertTrue(suggestion.getWeapon() == solution.getWeapon());
//		}
//		
//	}
	
	//Tests that player returns the one card to disprove the suggestion
	@Test
	public void testDisproveSuggestionOnePossible(){
		
	}
	
	//Tests that player randomly returns one of the cards to disprove the suggestion
	@Test
	public void testDisproveSuggestionTwoPossible(){
		
	}
	
	//tests that the players are queried in the correct order
	@Test
	public void testOrder(){
		
	}
	
	//Tests that the current player does not attempt to disprove suggestion
	@Test
	public void testAccusingPlayerNoCardReturn(){
		
	}
	
	//tests the human player disproving a suggestion
	@Test
	public void testHumanPlayer(){
		
	}
	
	//tests that a room is selected if possible
	@Test
	public void testTargetSelectionRoom(){
		
	}
	
	//tests that if no room, that a target is selected randomly
	@Test
	public void testTargetSelectionRandom(){
		
	}
	
	//test that takes a previously considered room in to consideration
	@Test
	public void testTargetSelectionPreviousRoom(){
		
	}
	
	//tests
	@Test
	public void testCompPlayerOneSuggestion(){
		
	}
	
	@Test
	public void testCompPlayerRandPossible(){
		
	}

}
