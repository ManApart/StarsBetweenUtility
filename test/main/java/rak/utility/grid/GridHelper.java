package rak.utility.grid;

public class GridHelper <T extends GridItem> {

	public <EnumType extends GridItemBuilder<T>> Grid<T> createGrid(EnumType[][] gridPattern){
		Grid<T> grid = new Grid<>(gridPattern.length);
		
		for (int x=0; x<gridPattern.length; x++){
			for (int y=0; y<gridPattern[x].length; y++){
				T tile = gridPattern[x][y].buildSquare();
				//Need to invert x and y in order to build a grid that matches a grid that is visually laid out
				grid.setAt(tile, y, x);
			}
		}
		return grid;
	};
}
