package clueGame;

public class BoardCell {
	protected int xPos;
	protected int yPos;
	protected char initial;
	protected boolean room;
	protected DoorDirection direction;
	public DoorDirection getDoorDirection() {
		return direction;
	}
	public char getInitial() {
		return initial;
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
	public BoardCell(int xPos, int yPos, char initial, DoorDirection direction) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.initial = initial;
		this.direction = direction;
	}
	public void setDirection(DoorDirection direction) {
		this.direction = direction;
	}
	@Override
	public String toString()
	{
		return "["+xPos+","+yPos+","+initial+","+direction+"]";
	}
}
