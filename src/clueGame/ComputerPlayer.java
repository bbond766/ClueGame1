package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> cardsInHand;
	
	public ComputerPlayer(){
		
	}
	
	public ComputerPlayer(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public void pickLocation(Set<BoardCell> targets){
		
	}
	
	public void makeAccusation(){
		
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		
	}
	
//	public ArrayList<Card> getCardsInHand() {
//		return cardsInHand;
//	}
//
//	public void setCardsInHand(ArrayList<Card> cardsInHand) {
//		this.cardsInHand = cardsInHand;
//	}

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
