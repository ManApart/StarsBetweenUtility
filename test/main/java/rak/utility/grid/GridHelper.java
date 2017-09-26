package rak.utility.grid;

public class GridHelper {

	public <EnumType extends GridItemBuilder> Grid<GridItem> createGrid(EnumType[][] gridPattern){
		Grid<GridItem> grid = new Grid<>(gridPattern.length);
		
		for (int x=0; x<gridPattern.length; x++){
			for (int y=0; y<gridPattern[x].length; y++){
				GridItem tile = gridPattern[x][y].buildSquare();
				grid.setAt(tile, x, y);
			}
		}
		return grid;
	};
}
