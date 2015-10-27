package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public abstract class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> cardsInHand;
	private ArrayList<Card> cardsNotSeen;
	private char roomIn;
	private boolean isComputerPlayer;
	private Solution suggestion;
	
	public Player(){
		this.cardsInHand = new ArrayList<Card>();
		this.cardsNotSeen = new ArrayList<Card>();
		this.isComputerPlayer = false;
		this.suggestion = null;
	}
	
	public Player(String playerName, int row, int column, Color color, char roomIn) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		this.cardsInHand = new ArrayList<Card>();
		this.cardsNotSeen = new ArrayList<Card>();
		this.roomIn = roomIn;
		this.isComputerPlayer = false;
		this.suggestion = null;
	}
	public void printCardsInHand(){
		for (int i = 0; i < cardsInHand.size(); i++){
			System.out.println(cardsInHand.get(i).getCardName() + " i " + i);
		}
	}

	public void loadDeck(ArrayList<Card> deck){
		cardsNotSeen = new ArrayList<Card>(deck);
		for (int i = 0; i<cardsInHand.size(); i++){
			deck.remove(cardsInHand.get(i));
		}
	}
	
	public void generatePossibleSolution(){	//TO DO
		BoardCell current;
		
	}
	public void pickLocation(Set<BoardCell> targets){}
	public void makeSuggestion(Board board, BoardCell location){}

	public ArrayList<Card> getCardsInHand() {
		return this.cardsInHand;
	}

	public void setCardsInHand(ArrayList<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	public void addCardToHand(Card card) {
		this.cardsInHand.add(card);
	}
	public Card disproveSuggestion(Solution suggestion){//TODO
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

	public void setRoomIn(char roomIn) {
		this.roomIn = roomIn;
	}
	public boolean getPlayerType(){
		return isComputerPlayer;
	}
	public Solution getSuggestion(){
		return suggestion;
	}
}
