package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HumanPlayer extends Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> cardsInHand;
	private char lastRoomVisited;
	private ArrayList<Card> cardsNotSeen;
	private boolean isComputerPlayer;
	private Solution suggestion;
	private boolean hasBeenQueried;
	
	public HumanPlayer(){
		super();
		this.isComputerPlayer = false;
		this.suggestion = null;
		this.cardsInHand = new ArrayList<Card>();
		this.hasBeenQueried = false;
	}
	public HumanPlayer(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		this.isComputerPlayer = false;
		this.suggestion = null;
		this.cardsInHand = new ArrayList<Card>();
		this.hasBeenQueried = false;
	}
	
	public boolean isHasBeenQueried() {
		return hasBeenQueried;
	}
	public void setHasBeenQueried(boolean hasBeenQueried) {
		this.hasBeenQueried = hasBeenQueried;
	}
	@Override
	public void pickLocation(Set<BoardCell> targets){
		//to come
	}
	
	@Override
	public void makeAccusation(String p, String r, String w){
		Solution soln = new Solution(p, r, w);
		suggestion = soln;
	}
	
	@Override
	public void makeSuggestion(Board board, BoardCell location, String p, String w){
		char initial = location.getInitial();
		HashMap<Character, String> roomsCopy = new HashMap<Character, String>(board.getRooms());
		String r = roomsCopy.get(initial);
			
		Solution guess = new Solution(p, r, w);
		suggestion = guess;	
	}
	@Override
	public boolean isComputerPlayer(){
		return false;
	}
	@Override
	public Card disproveSuggestion(Solution suggestion){
		boolean inHand = false;
		Card cardShown = new Card();
		ArrayList<Card> cardsToShow = new ArrayList<Card>();
		//System.out.println("Cards in hand size : " + this.cardsInHand.size());
		for (int i = 0; i < cardsInHand.size(); i++){
			if (cardsInHand.get(i).getCardName() == suggestion.person || cardsInHand.get(i).getCardName() == suggestion.weapon
					|| cardsInHand.get(i).getCardName() == suggestion.room){
				cardsToShow.add(cardsInHand.get(i));
				inHand = true;
			}	
		}		
		//System.out.println(cardsToShow.size() + " size");
		if (inHand){
			int index = (int) Math.floor(Math.random()* cardsToShow.size());
//			System.out.println(index + "CARDS TO SHOW");
			return cardsToShow.get(index);
		}
		return null;
	}
	public Solution getSuggestion() {
		return suggestion;
	}
	
	
}
