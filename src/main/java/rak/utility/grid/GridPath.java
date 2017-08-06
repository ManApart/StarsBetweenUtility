package rak.utility.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GridPath<E extends GridItem> {
	private GridSquare<E> source;
	private HashMap<Integer, ArrayList<GridSquare<E>>> squaresAtDistanceMap = new HashMap<>();
	private HashMap<GridSquare<E>, Integer> minDistanceAtSquareMap = new HashMap<>();
	private HashMap<GridSquare<E>, ArrayList<Integer>> distanceAtSquareMap = new HashMap<>();
	private ArrayList<Route<E>> routes;

	public GridPath(GridSquare<E> source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Path of " + source;
	}

	public GridSquare<E> getSource() {
		return source;
	}

	public int getMaxDistance() {
		return Collections.max(squaresAtDistanceMap.keySet());
	}

	/**
	 * Returns the distance to the destination. If the destination is not part
	 * of this path, then returns -1
	 */
	public int getMinDistanceTo(GridSquare<E> destination) {
		if (pathContains(destination)) {
			return minDistanceAtSquareMap.get(destination);
		}
		return -1;
	}

	private boolean pathContains(GridSquare<E> destination) {
		return minDistanceAtSquareMap.containsKey(destination);
	}

	public ArrayList<GridSquare<E>> getSquaresAt(int distance) {
		ArrayList<GridSquare<E>> squares = new ArrayList<>();
		if (squaresAtDistanceMap.containsKey(distance)) {
			squares.addAll(squaresAtDistanceMap.get(distance));
		}
		return squares;
	}

	public ArrayList<E> getItemsAt(int distance) {
		ArrayList<E> items = new ArrayList<>();
		for (GridSquare<E> square : getSquaresAt(distance)) {
			items.add(square.getItem());
		}
		return items;
	}
	
	protected void setDistance(GridSquare<E> square, int distance) {
		removeNonDestinationDuplicates(square, distance);
		addSquareAtDistance(square, distance);
		addDistanceAtSquare(square, distance);
		addMinDistance(square, distance);
	}

	private void removeNonDestinationDuplicates(GridSquare<E> square, int distance) {
		if (newSquareIsCloserThanExisting(square, distance) && squareIsNotADestination(square)){
			//Clear out the old values
			squaresAtDistanceMap.put(distance, new ArrayList<GridSquare<E>>());
			distanceAtSquareMap.put(square, new ArrayList<Integer>());
			minDistanceAtSquareMap.put(square, distance);
		}
	}

	private boolean squareIsNotADestination(GridSquare<E> square) {
		return square.getItem() == null || square.getItem().getPathingType() != PathingType.DESTINATION;
	}

	private boolean newSquareIsCloserThanExisting(GridSquare<E> square, int distance) {
		return distance < getMinDistanceTo(square);
	}
	
	private void addSquareAtDistance(GridSquare<E> square, int distance) {
		if (!squaresAtDistanceMap.containsKey(distance)) {
			squaresAtDistanceMap.put(distance, new ArrayList<GridSquare<E>>());
		}
		squaresAtDistanceMap.get(distance).add(square);
	}

	private void addDistanceAtSquare(GridSquare<E> square, int distance) {
		if (!distanceAtSquareMap.containsKey(square)) {
			distanceAtSquareMap.put(square, new ArrayList<Integer>());
		}
		distanceAtSquareMap.get(square).add(distance);
	}
	
	private void addMinDistance(GridSquare<E> square, int distance) {
		if (!pathContains(square)){
			minDistanceAtSquareMap.put(square, distance);
		}
	}

	public ArrayList<Route<E>> getRoutes() {
		return new ArrayList<>(routes);
	}

	protected void setRoutes(ArrayList<Route<E>> routes) {
		this.routes = routes;
	}

}
