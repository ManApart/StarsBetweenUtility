package rak.utility.grid;

import java.util.ArrayList;

public class GridPathBuilder<E extends GridItem> {
	private Grid<E> grid;
	
	public GridPathBuilder(Grid<E> grid){
		this.grid = grid;
	}
	
	
	public GridPath<E> buildPath(GridSquare<E> source){
		GridPath<E> path = new GridPath<E>(source);
		
		createDistanceMap(grid, path);
		
		RouteBuilder<E> routeBuilder = new RouteBuilder<>(grid, path);
		routeBuilder.buildRoutes();
		path.setRoutes(routeBuilder.getRoutes());
		
		return path;
	}
	
	private void createDistanceMap(Grid<E> grid, GridPath<E> path) {
		ArrayList<GridSquare<E>> open = new ArrayList<>();
		ArrayList<GridSquare<E>> closed = new ArrayList<>();
		int distance = 0;
		path.setDistance(path.getSource(), distance);
		open.add(path.getSource());

		while (open.size() > 0) {
			GridSquare<E> current = open.get(0);
			open.remove(current);
			addNonDestinationTilesToClosedList(path, closed, current);
			// Because we may iterate multiple directions, distance++ could be
			// out of sync with the true distance.
			distance = path.getMinDistanceTo(current) + 1;

			if (shouldAddNeighbors(path, current)) {
				ArrayList<GridSquare<E>> validNeighbors = getValidNeighbors(current, grid, closed);
				for (GridSquare<E> square : validNeighbors) {
					path.setDistance(square, distance);
					open.add(square);
				}
			}
		}
	}

	private void addNonDestinationTilesToClosedList(GridPath<E> path, ArrayList<GridSquare<E>> closed, GridSquare<E> current) {
		if (current == path.getSource() || current.getItem() == null || current.getItem().getPathingType() != PathingType.DESTINATION) {
			closed.add(current);
		}
	}

	private boolean shouldAddNeighbors(GridPath<E> path, GridSquare<E> current) {
		// Only get neighbors for Open path squares, or for the source square
		return current == path.getSource() || (current.getItem() != null && current.getItem().getPathingType() == PathingType.OPEN);
	}

	private ArrayList<GridSquare<E>> getValidNeighbors(GridSquare<E> current, Grid<E> grid, ArrayList<GridSquare<E>> closed) {
		ArrayList<GridSquare<E>> neighbors = grid.findSurroundingRingOfSquares(current, 1);

		ArrayList<GridSquare<E>> validNeighbors = new ArrayList<>();
		for (GridSquare<E> square : neighbors) {
			if (isValidForPathing(square, closed)) {
				validNeighbors.add(square);
			}
		}
		return validNeighbors;
	}
	
	private boolean isValidForPathing(GridSquare<E> square, ArrayList<GridSquare<E>> closed) {
		return square.getItem() != null && PathingType.includeInPath(square.getItem().getPathingType()) && !closed.contains(square);
	}

}
