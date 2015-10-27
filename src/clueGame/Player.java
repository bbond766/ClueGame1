package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> cardsInHand;
	private ArrayList<Card> cardsSeen;
	
	public Player(){
		this.cardsInHand = new ArrayList<Card>();
		this.cardsSeen = new ArrayList<Card>();
	}
	
	public Player(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		this.cardsInHand = new ArrayList<Card>();
		this.cardsSeen = new ArrayList<Card>();
	}
	public void printCardsInHand(){
		for (int i = 0; i < cardsInHand.size(); i++){
			System.out.println(cardsInHand.get(i).getCardName() + " i " + i);
		}
	}
	//Add newly seen card to ArrayList detailing all cards that this player has seen, and therefore knows are not part of possible solution
	public void addSeenCard(Card seen){
		cardsSeen.add(seen);
	}
	//HELPER FUNCTION: Check if a given card has been seen already and therefore cannot be part of a solution. 
	public boolean checkIfSeen(Card check){
		return (cardsSeen.contains(check));
	}
	public void generatePossibleSolution(){	//TO DO
		//should it be based on what room the player is in?
		int room = 0;
		int person = 0;
		int weapon = 0;
		String roomGuess, personGuess, weaponGuess;
		for (int i = 0; i < cardsSeen.size(); i++){
			if (cardsSeen.get(i).getType() == CardType.ROOM){
				room++;
			}
			else if (cardsSeen.get(i).getType() == CardType.PERSON){
				person++;
			}
			else if (cardsSeen.get(i).getType() == CardType.WEAPON){
				weapon++;
			}
			else{
				System.out.println("Invalid card type held in cardsSeen hand.");
			}
		}
		if (room == 8){
			//room soln known
//			roomGuess = 
			
		}
		if (person == 5){
			
		}
		if (weapon == 5){
			
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
