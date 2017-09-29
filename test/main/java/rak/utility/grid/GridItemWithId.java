package rak.utility.grid;

public class GridItemWithId implements GridItem {
	private int id;
	
	public GridItemWithId(int id) {
		this.id = id;
	}

	@Override
	public PathingType getPathingType() {
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof GridItemWithId){
			GridItemWithId other = (GridItemWithId) obj;
			return id == other.id;
		}
		return false;
	}
	
	public int getId(){
		return id;
	}

}
