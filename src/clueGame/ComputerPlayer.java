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
	
	public ComputerPlayer(){
		super();
		this.isComputerPlayer = true;
		this.suggestion = null;
		this.cardsInHand = new ArrayList<Card>();
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
			int index = (int) (Math.random()%targets.size());
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
		char initial = location.getInitial();
		HashMap<Character, String> roomsCopy = new HashMap<Character, String>(board.getRooms());
		String currentRoom = roomsCopy.get(initial);
		
		int i = (int) Math.random() % cardsNotSeen.size();
		while (cardsNotSeen.get(i).getType() != CardType.PERSON){
			i = (int) Math.random() % cardsNotSeen.size();
		}
		String personGuess = cardsNotSeen.get(i).getCardName();
		i = (int) Math.random() % cardsNotSeen.size();;
		while (cardsNotSeen.get(i).getType() != CardType.WEAPON){
			i = (int) Math.random() % cardsNotSeen.size();
		}
		String weaponGuess = cardsNotSeen.get(i).getCardName();
		
		Solution guess = new Solution(personGuess, currentRoom, weaponGuess);
		suggestion = guess;	
	}
	@Override
	public boolean isComputerPlayer(){
		return true;
	}
	@Override
	public Card disproveSuggestion(Solution suggestion){
		boolean inHand = false;
		Card cardShown = new Card();
		ArrayList<Card> cardsToShow = new ArrayList<Card>();
		System.out.println("CARDS IN HAND SIZE " + cardsInHand.size());
		for (int i = 0; i < cardsInHand.size(); i++){
			if (cardsInHand.get(i).getCardName() == suggestion.person || cardsInHand.get(i).getCardName() == suggestion.weapon
					|| cardsInHand.get(i).getCardName() == suggestion.room){
				cardsToShow.add(cardsInHand.get(i));
				inHand = true;
			}	
		}
		if (inHand){
		//	int index = (int) Math.random() % cardsToShow.size();
			return cardsToShow.get(0);
		}
		return null;
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
}
