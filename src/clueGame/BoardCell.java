package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoardCell {
	protected int xPos;
	protected int yPos;
	protected char initial;
	protected boolean room;
	protected boolean displayName;
	protected DoorDirection direction;
	protected int rowPixel, colPixel;
	public static final int DIMENSION_X = Board.PIXEL_BOARD_SIZE/26;
	public static final int DIMENSION_Y = Board.PIXEL_BOARD_SIZE/26;
	
	public BoardCell(int xPos, int yPos, char initial, DoorDirection direction) {
		super();
		this.yPos = xPos;
		this.xPos = yPos;
		this.initial = initial;
		this.direction = direction;
		this.displayName = false;
	}
	
	public boolean isDisplayName() {
		return displayName;
	}


	public void setDisplayName(boolean displayName) {
		this.displayName = displayName;
	}


	public DoorDirection getDoorDirection() {
		return direction;
	}
	public char getInitial() {
		return initial;
	}
	public int getRow() {
		return yPos;
	}
	public int getColumn() {
		return xPos;
	}
	public boolean isWalkway()
	{
		return initial == 'W';
	}
	public boolean isRoom()
	{
		return (initial != 'X' && initial != 'W');
	}
	public boolean isDoorway()
	{
		return (direction != DoorDirection.NONE);
	}
	public void setDirection(DoorDirection direction) {
		this.direction = direction;
	}
	@Override
	public String toString()
	{
		return "["+xPos+","+yPos+","+initial+","+direction+"]";
	}	
	public int getRowPixel() {
		return rowPixel;
	}
	public void setRowPixel(int rowPixel) {
		this.rowPixel = rowPixel;
	}
	public int getColPixel() {
		return colPixel;
	}
	public void setColPixel(int colPixel) {
		this.colPixel = colPixel;
	}
	public void draw(Graphics g){
		if(displayName){
			String name = Board.getRooms().get(initial);
			g.drawString(name, xPos*DIMENSION_X, yPos*DIMENSION_Y);
		}
		Graphics2D g2 = (Graphics2D) g;
		if(!isRoom()){
			g2.setColor(Color.ORANGE);
			g2.fillRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
		}
		if(initial == 'X'){
			g2.setColor(Color.RED);
			g2.fillRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
		}
		else if(direction == DoorDirection.LEFT){
			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X, yPos*DIMENSION_Y+DIMENSION_Y);
		}
		else if(direction == DoorDirection.RIGHT){
			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(xPos*DIMENSION_X+ DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X + DIMENSION_X, yPos*DIMENSION_Y-DIMENSION_Y);
		}
		else if(direction == DoorDirection.UP){
			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X+DIMENSION_X, yPos*DIMENSION_Y);
		}
		else if(direction == DoorDirection.DOWN){
			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y + DIMENSION_Y, xPos*DIMENSION_X+DIMENSION_X, yPos*DIMENSION_Y + DIMENSION_Y);
		}
	}

}
