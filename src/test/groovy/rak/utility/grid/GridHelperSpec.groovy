package rak.utility.grid

import spock.lang.Specification

class GridHelperSpec extends Specification {
	
	def "Grid helper builds a grid and gets id #id for item at x #x and y #y"(int id, int x, int y){
		given:
			GridHelper helper = new GridHelper();
			Grid<GridItemWithId> grid = helper.createGrid(GridIdItemTypes.GRID)

		when:
			GridItemWithId actual = grid.getItemAt(x,y)

		then:
			actual.getId() == id
			
		where:
			id 	| 	x	|	y
			1	|	0	|	0
			2	|	1	|	0
			3	|	2	|	0
			4	|	0	|	1
			5	|	1	|	1
			6	|	2	|	1
			7	|	0	|	2
			8	|	1	|	2
			9	|	2	|	2
	}
	
	def "Grid direction #direction picks the correct id #id"(int id, GridDirection direction){
		given:
			GridHelper helper = new GridHelper();
			Grid<GridItemWithId> grid = helper.createGrid(GridIdItemTypes.GRID)
			GridItemWithId start = grid.getItemAt(1, 1)
		when:
			GridItemWithId actual = grid.getNeighbor(start, direction)

		then:
			actual.getId() == id
			
		where:
			id 	| 	direction
			1	|	GridDirection.NORTH_WEST
			2	|	GridDirection.NORTH
			3	|	GridDirection.NORTH_EAST
			4	|	GridDirection.WEST
			
			6	|	GridDirection.EAST
			7	|	GridDirection.SOUTH_WEST
			8	|	GridDirection.SOUTH
			9	|	GridDirection.SOUTH_EAST
	}
}
