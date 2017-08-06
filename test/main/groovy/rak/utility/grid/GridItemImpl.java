package rak.utility.grid;

public class GridItemImpl implements GridItem {
	private PathingType validForPathing;
	
	public GridItemImpl(PathingType validForPathing) {
		this.validForPathing = validForPathing;
	}
	

	@Override
	public PathingType getPathingType() {
		return validForPathing;
	}

}
