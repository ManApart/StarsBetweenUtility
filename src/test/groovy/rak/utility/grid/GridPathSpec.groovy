package rak.utility.grid

import spock.lang.Specification

class GridPathSpec extends Specification {

    def "Source matches"() {
        given:
            Grid<GridItemImpl> grid = new Grid<GridItemImpl>(2)
            GridSquare<GridItemImpl> source = grid.getSquareAt(0, 0)
        when:
            GridPath<GridItemImpl> path = new GridPath<GridItemImpl>(source)
        then:
            source == path.getSource()
    }

    def "Nearest Neighbors"() {
        given:
            Grid<GridItemImpl> grid = new Grid<GridItemImpl>(2)
            GridSquare<GridItemImpl> source = grid.getSquareAt(0, 0)
            for (GridSquare<GridItemImpl> square : grid.getAllSquares()) {
                square.setItem(new GridItemImpl(PathingType.OPEN))
            }
        when:
            GridPathBuilder builder = new GridPathBuilder(grid)
            GridPath<GridItemImpl> path = builder.buildPath(source)
        then:
            assert path.getSquaresAt(0).contains(grid.getSquareAt(0, 0))
            assert path.getSquaresAt(1).contains(grid.getSquareAt(1, 0))
            assert path.getSquaresAt(1).contains(grid.getSquareAt(0, 1))
            assert path.getSquaresAt(2).contains(grid.getSquareAt(1, 1))
    }

}
