package rak.utility.grid;

public class GridItemImpl implements GridItem {
	private PathingType pathingType;
	private String string;
	
	public GridItemImpl(PathingType validForPathing) {
		this.pathingType = validForPathing;
	}

	@Override
	public PathingType getPathingType() {
		return pathingType;
	}

	public void setString(String string) {
	    this.string = string;
    }

}
