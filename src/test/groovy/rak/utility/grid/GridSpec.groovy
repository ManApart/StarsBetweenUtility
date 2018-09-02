package rak.utility.grid

import spock.lang.Specification

class GridSpec extends Specification{

	
	
	def "Get All Surrounding squares includes inner layers"(){
		given:
			Grid<GridItemImpl> grid = new Grid<>(size)
			
			GridSquare<GridItemImpl> origin = grid.getSquareAt(center,center)
			
		when:
			ArrayList<GridSquare<GridItemImpl>> squares = grid.findAllSurroundingSquares(origin, area)
			
		then:
			squares.contains(origin)
			squares.size() == expectedCount
			
		where:
			size	|	area	|	center	|	expectedCount
			1		|	0		|	0		|	1
			3		|	1		|	1		|	5
			10		|	2		|	4		|	13
	}
	
	def "Get Surrounding ring excludes inner layers"(){
		given:
			Grid<GridItemImpl> grid = new Grid<>(size)
			
			GridSquare<GridItemImpl> origin = grid.getSquareAt(center,center)
			
		when:
			ArrayList<GridSquare<GridItemImpl>> squares = grid.findSurroundingRingOfSquares(origin, area)
			
		then:
			!squares.contains(origin)
			squares.size() == expectedCount
			
		where:
			size	|	area	|	center	|	expectedCount
			1		|	0		|	0		|	0
			3		|	1		|	1		|	4
			10		|	2		|	4		|	8
	}
	
	def "Get all items returns same number of items as get all squares"(){
		given:
			Grid<GridItemImpl> grid = new Grid<>(size)
			
		when:
			for (int x=0; x<size; x++){
				for (int y=0; y<size; y++){

                    GridItemImpl item = new GridItemImpl()
                    item.string = "item " + x +", " + y
					grid.setAt(item,x,y)
				}
			}

		then:
			int totalSize = size*size
			grid.getSizeInOneDimension() == size
			grid.getAllSquares().size() == totalSize
			grid.getAllItems().size() == totalSize
			
		where:
			size	|_
			10		|_	
			1		|_	
			3		|_	
	}
	
	def "Get all items does not return null items"(){
		given:
			Grid<GridItemImpl> grid = new Grid<>(size)
	
		when:
			for (int x=0; x<size; x++){
				for (int y=0; y<size; y++){
					//Don't add the first one
					if (y != 0){
                        GridItemImpl item = new GridItemImpl()
                        item.string = "item " + x +", " + y
						grid.setAt(item,x,y)
					}
				}
			}
		
		then:
			int totalSize = size*size
			int totalMinusGaps = totalSize - size
			grid.getSizeInOneDimension() == size
			grid.getAllSquares().size() == totalSize
			grid.getAllItems().size() == totalMinusGaps
			
			where:
				size	|_
				10		|_	
				1		|_	
				3		|_	
	}
}
