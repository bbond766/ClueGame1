
public class BoardCell {
	int xPos;
	int yPos;
	public BoardCell(int xPos, int yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}
	@Override
	public String toString()
	{
		return "["+xPos+","+yPos+"]";
	}
}
