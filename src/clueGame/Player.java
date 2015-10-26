package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> cardsInHand;
	
	public Player(){
		this.cardsInHand = new ArrayList<Card>();
	}
	
	public Player(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		this.cardsInHand = new ArrayList<Card>();
	}
	public void printCardsInHand(){
		for (int i = 0; i < cardsInHand.size(); i++){
			System.out.println(cardsInHand.get(i).getCardName() + " i " + i);
		}
	}
	public ArrayList<Card> getCardsInHand() {
		return this.cardsInHand;
	}

	public void setCardsInHand(ArrayList<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	public void addCardToHand(Card card) {
		this.cardsInHand.add(card);
	}
	public Card disproveSuggestion(Solution suggestion){
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
	
}
