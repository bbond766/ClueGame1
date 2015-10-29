package clueTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.DoorDirection;
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

		ArrayList<Card> copyDeck = new ArrayList<Card>(board.getDeck());
		
		int index = 0;
		solnPerson = board.getDeck().get(index).getCardName();
		copyDeck.remove(index);
		index = 6;
		solnRoom = board.getDeck().get(index).getCardName();
		copyDeck.remove(index-1);
		index = 15;
		solnWeapon = board.getDeck().get(index).getCardName();
		copyDeck.remove(index-2);
		
		solution.setPerson(solnPerson);
		solution.setRoom(solnRoom);
		solution.setWeapon(solnWeapon);
//		System.out.println(solnPerson + " " + solnRoom + " " + solnWeapon);
		players =  new ArrayList<Player>(board.getPlayers());
		
		while (copyDeck.size() != 0){
			for (int i = 0; i < players.size(); i++){
				if (copyDeck.size() == 0){
					return;
				}
				players.get(i).addCardToHand(copyDeck.get(0));
				copyDeck.remove(0);
			}
		}
		for (int i = 0; i < players.size(); i++){
			for (int j = 0; j < players.get(i).getCardsInHand().size(); j++){
//				System.out.println("player ith " + i + players.get(i).getCardsInHand().get(j).getCardName() + " j " + j);
			}
		}
	}
	
	@Test
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
		System.out.println(suggestion.getRoom());
		
		assertEquals(solution.getRoom(), suggestion.getRoom());
		assertEquals(solution.getPerson(), suggestion.getPerson());
		assertEquals(solution.getWeapon(), suggestion.getWeapon());
	}
	
	//Tests that player returns the one card to disprove the suggestion
	@Test
	public void testDisproveSuggestionOnePossible(){
		Player player = players.get(0);	//this is a human player
		player.makeAccusation("Ms. Scarlet", "Conservatory", "Lead Pipe");	//player 1 should show Scarlet card to disprove
		Solution suggestion = player.getSuggestion();
//		System.out.println(suggestion.getPerson());

		Player respondingPlayer = players.get(1);
		Card showCard = new Card("Ms. Scarlet", CardType.PERSON);
		respondingPlayer.addCardToHand(showCard);
		System.out.println(respondingPlayer.getCardsInHand().get(0).getCardName());
		Card cardShown = new Card();
		cardShown = respondingPlayer.disproveSuggestion(suggestion);	//getCardsInHand().get(0); //
		System.out.println("NAME + " + cardShown.getCardName());
		System.out.println("here");
		
		for (int i = 1; i < players.size(); i++){

//			Player play = board.getPlayers().get(i);
//			if (play.disproveSuggestion(suggestion) != null){
//				System.out.println("Card found.");
//				return;
//			}
//			else{
//				continue;
//			}
		}
//		Player play = board.getPlayers().get(1);
//		Card cardShown = play.disproveSuggestion(suggestion);
		
//		assertEquals(cardShown.getCardName(), "Ms. Scarlet");
//		assertEquals(solution.getPerson(), suggestion.getPerson());
//		assertEquals(solution.getWeapon(), suggestion.getWeapon());
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
	
	//tests the computer player making a suggestion with only one possibility
	@Test
	public void testCompPlayerOneSuggestion(){
		ComputerPlayer cp = new ComputerPlayer("Col. Mustard", 0, 0, Color.BLACK);
		ArrayList<Card> cardsNotSeenTest = new ArrayList<Card>();
		Card person = new Card("Ms. Scarlette", CardType.PERSON);
		cardsNotSeenTest.add(person);
		Card weapon = new Card("Lead Pipe", CardType.WEAPON);
		cardsNotSeenTest.add(weapon);
		cp.setCardsNotSeen(cardsNotSeenTest);
		//cp.makeSuggestion(board, cp.);
		assertEquals(cp.getSuggestion().person, "Ms. Scarlette");
		assertEquals(cp.getSuggestion().weapon, "Lead Pipe");
		assertEquals(cp.getSuggestion().room, "Dining Room");
	}
	
	@Test
	public void testCompPlayerRandPossible(){
		
	}

}
