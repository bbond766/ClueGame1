
public class BoardCell {
	int xPos;
	int yPos;
	char initial;

	private DoorDirection direction;
	public DoorDirection getDirection() {
		return direction;
	}
	boolean isWalkway()
	{
		return false;
	}
	boolean isRoom()
	{
		return false;
	}
	boolean isDoorway()
	{
		return false;
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
		return "["+xPos+","+yPos+"]";
	}
}
