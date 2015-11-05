package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class BoardCell {
	protected int xPos;
	protected int yPos;
	protected char initial;
	protected boolean room;
	protected DoorDirection direction;
	protected int rowPixel, colPixel;
	public static final int DIMENSION_X = Board.PIXEL_BOARD_SIZE/26;
	public static final int DIMENSION_Y = Board.PIXEL_BOARD_SIZE/26;
	
	public BoardCell(int xPos, int yPos, char initial, DoorDirection direction) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.initial = initial;
		this.direction = direction;
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
		if(!isRoom()){
			g.setColor(Color.YELLOW);
			g.fillRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
			g.setColor(Color.BLACK);
			g.drawRect(xPos*DIMENSION_X, yPos*DIMENSION_Y, DIMENSION_Y, DIMENSION_X);
		}
		else if(direction == DoorDirection.LEFT){
			g.setColor(Color.BLUE);
			g.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X, yPos*DIMENSION_Y+DIMENSION_Y);
		}
		else if(direction == DoorDirection.RIGHT){
			g.setColor(Color.BLUE);
			g.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X, yPos*DIMENSION_Y-DIMENSION_Y);
		}
		else if(direction == DoorDirection.UP){
			g.setColor(Color.BLUE);
			g.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X+DIMENSION_X, yPos*DIMENSION_Y);
		}
		else if(direction == DoorDirection.DOWN){
			g.setColor(Color.BLUE);
			g.drawLine(xPos*DIMENSION_X, yPos*DIMENSION_Y, xPos*DIMENSION_X-DIMENSION_X, yPos*DIMENSION_Y);
		}
	}

}
