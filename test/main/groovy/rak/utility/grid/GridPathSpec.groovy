package rak.utility.grid

import spock.lang.Specification

class GridPathSpec extends Specification {
	
	def "Source matches"(){
		given:
			Grid<GridItemImpl> grid = new Grid<GridItem>(2)
			GridSquare<GridItemImpl> source = grid.getSquareAt(0,0)
		when:
			GridPath<GridItemImpl> path = new GridPath<GridItemImpl>(source, grid)
		then:
			source == path.getSource()
	}
	
	def "Nearest Neighbors"(){
		given:
			Grid<GridItemImpl> grid = new Grid<GridItem>(2)
			GridSquare<GridItemImpl> source = grid.getSquareAt(0,0)
			for (GridSquare<GridItemImpl> square : grid.getAllSquares()){
				square.setItem(new GridItemImpl(true))
			}
		when:
			GridPath<GridItemImpl> path = new GridPath<GridItemImpl>(source, grid)
		then:
			source == path.getSource()
			assert path.getSquaresAt(0).contains(grid.getSquareAt(0,0))
			assert path.getSquaresAt(1).contains(grid.getSquareAt(1,0))
			assert path.getSquaresAt(1).contains(grid.getSquareAt(0,1))
			assert path.getSquaresAt(2).contains(grid.getSquareAt(1,1))
	}

}
