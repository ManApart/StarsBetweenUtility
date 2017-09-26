package rak.utility.grid;

public enum GridDirection {
	NORTH(0,1),
	EAST(-1,0),
	SOUTH(0,-1),
	WEST(1,0);
	
	private int offsetX;
	private int offsetY;
	
	private GridDirection(int x, int y){
		this.offsetX = x;
		this.offsetY = y;
	}

	public int getOffsetX(){
		return offsetX;
	}
	
	public int getOffsetY(){
		return offsetY;
	}
}
