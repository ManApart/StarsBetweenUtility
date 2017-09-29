package rak.utility.grid;

public enum GridDirection {
	NORTH_WEST(-1,-1),
	NORTH(0,-1),
	NORTH_EAST(1,-1),
	EAST(1,0),
	SOUTH_WEST(-1,1),
	SOUTH(0,1),
	SOUTH_EAST(1,1),
	WEST(-1,0);
	
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
