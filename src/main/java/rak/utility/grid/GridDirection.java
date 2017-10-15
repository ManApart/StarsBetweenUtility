package rak.utility.grid;

public enum GridDirection {
	NORTH_WEST(-1,-1, false),
	NORTH(0,-1, true),
	NORTH_EAST(1,-1, false),
	EAST(1,0, true),
	SOUTH_WEST(-1,1, false),
	SOUTH(0,1, true),
	SOUTH_EAST(1,1, false),
	WEST(-1,0, true);
	
	private int offsetX;
	private int offsetY;
	private boolean isCardinal;
	
	private GridDirection(int x, int y, boolean isCardinal){
		this.offsetX = x;
		this.offsetY = y;
		this.isCardinal = isCardinal;
	}

	public int getOffsetX(){
		return offsetX;
	}
	
	public int getOffsetY(){
		return offsetY;
	}
	
	public boolean isDiagonal(){
		return !isCardinal;
	}
	
	public boolean isCardinal(){
		return isCardinal;
	}
	
	public GridDirection getInverse(){
		int x = -1 * getOffsetX();
		int y = -1 * getOffsetY();
		return byValue(x, y);
	}
	
	public static GridDirection byValue(int x, int y){
		if (x > 1 || x < -1 || y > 1 || y < -1){
			throw new IllegalArgumentException("Direction values x:" + x +", y:" + y + " are out of bounds");
		}
		for (GridDirection direction : GridDirection.values()){
			if (x == direction.getOffsetX() && y == direction.getOffsetY()){
				return direction;
			}
		}
		return null;
	}
}
