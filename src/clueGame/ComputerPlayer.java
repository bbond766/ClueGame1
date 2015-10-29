package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComputerPlayer extends Player {
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
	
	public ComputerPlayer(){
		super();
		this.isComputerPlayer = true;
		this.suggestion = null;
		this.cardsInHand = new ArrayList<Card>();
		this.hasBeenQueried = false;
	}
	
	public ComputerPlayer(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		this.isComputerPlayer = true;
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
		boolean moved = false;
		for(BoardCell x : targets){
			if (x.isDoorway() && x.getInitial()!=lastRoomVisited){
				row = x.yPos;
				column = x.xPos;
				lastRoomVisited = x.getInitial();
				moved = true;
			}
		}
		if(!moved){
			int index = (int) Math.floor(Math.random()*targets.size());
			int i = 0;
			for(BoardCell x : targets){
				if(i<index){
					i++;
				}
				else{
					row = x.yPos;
					column =x.xPos;
					moved = true;
				}
			}
		}
	}
	
	public void makeAccusation(){
		Solution soln = new Solution();
		if (cardsNotSeen.size() == 3 && cardsNotSeen.get(0).getType() != cardsNotSeen.get(1).getType() && 
				cardsNotSeen.get(0).getType() != cardsNotSeen.get(2).getType() && cardsNotSeen.get(1).getType() !=
				cardsNotSeen.get(2).getType()){
			//Time to make an accusation (should be correct)

			String person = null, room = null, weapon = null;
			for (int i = 0; i < cardsNotSeen.size(); i++){
				CardType current = cardsNotSeen.get(i).getType();
				if (current == CardType.PERSON){
					person = cardsNotSeen.get(i).getCardName();
				}
				else if (current == CardType.WEAPON){
					weapon  = cardsNotSeen.get(i).getCardName();
				}
				else if (current == CardType.ROOM){
					room  = cardsNotSeen.get(i).getCardName();
				}
			}
			soln = new Solution(person, room, weapon);
		}
		suggestion = soln;
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		ArrayList<Card> cardsNotSeenCopy = new ArrayList<Card>(cardsNotSeen);
		char initial = location.getInitial();
		HashMap<Character, String> roomsCopy = new HashMap<Character, String>(board.getRooms());
		String currentRoom = roomsCopy.get(initial);
		String weaponGuess;
		int i = (int) Math.floor(Math.random()*cardsNotSeenCopy.size());
		while (cardsNotSeenCopy.get(i).getType() != CardType.PERSON){
			i = (int) Math.floor(Math.random()*cardsNotSeenCopy.size());
		}
		String personGuess = cardsNotSeenCopy.get(i).getCardName();
		cardsNotSeenCopy.remove(i);
		if(cardsNotSeenCopy.size() == 1){
			weaponGuess = cardsNotSeenCopy.get(0).getCardName();
		}
		else{
			i = (int) Math.floor(Math.random()*cardsNotSeenCopy.size());
			while (cardsNotSeenCopy.get(i).getType() != CardType.WEAPON){
				cardsNotSeenCopy.remove(i);
				i = (int) Math.floor(Math.random()*cardsNotSeenCopy.size());
			}
			weaponGuess = cardsNotSeenCopy.get(i).getCardName();
		}
		
		
		Solution guess = new Solution(personGuess, currentRoom, weaponGuess);
		suggestion = guess;	
	}
	@Override
	public boolean isComputerPlayer(){
		return true;
	}
	
	public void addCardToHand(Card card) {
		this.cardsInHand.add(card);
	}
	
	@Override
	public Card disproveSuggestion(Solution suggestion){
		boolean inHand = false;
		Card cardShown = new Card();
		ArrayList<Card> cardsToShow = new ArrayList<Card>();
		for (int i = 0; i < cardsInHand.size(); i++){
			if (cardsInHand.get(i).getCardName() == suggestion.person || cardsInHand.get(i).getCardName() == suggestion.weapon || cardsInHand.get(i).getCardName() == suggestion.room){
				cardsToShow.add(cardsInHand.get(i));
				inHand = true;
			}	
		}
		if (inHand){
			int index = (int) Math.floor(Math.random()* cardsToShow.size());
			return cardsToShow.get(index);
		}
		else{
			return null;
		}
	}
	
	public String getName(){
		return playerName;
	}
	
	public void setName(String name){
		playerName = name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public boolean getPlayerType(){
		return isComputerPlayer;
	}
	public Solution getSuggestion(){
		return suggestion;
	}
	public void setCardsNotSeen(ArrayList<Card> cardsNotSeen) {
		this.cardsNotSeen = cardsNotSeen;
	}
}
