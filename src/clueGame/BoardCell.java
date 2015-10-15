package clueGame;

public class BoardCell {
	int xPos;
	int yPos;
	char initial;
	boolean room;
	private DoorDirection direction;
	public DoorDirection getDoorDirection() {
		return direction;
	}
	public char getInitial() {
		return initial;
	}
	boolean isWalkway()
	{
		return initial == 'W';
	}
	boolean isRoom()
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
