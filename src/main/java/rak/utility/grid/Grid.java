package rak.utility.grid;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid<E extends GridItem> {
	
	private ArrayList<ArrayList<GridSquare<E>>> grid;
	private HashMap<E, GridSquare<E>> itemToSquareMap;
	
	public Grid(int size){
		grid = populateEmptyGrid(size);
		refreshMaps();
	}

	public void refreshMaps() {
		propulateItemToSquareMap();
		createPaths();
	}

	private ArrayList<ArrayList<GridSquare<E>>> populateEmptyGrid(int size) {
		ArrayList<ArrayList<GridSquare<E>>> grid = new ArrayList<ArrayList<GridSquare<E>>>(); 
		for (int x=0; x<size; x++){
			ArrayList<GridSquare<E>> innerList = new ArrayList<GridSquare<E>>();
			for (int y=0; y<size; y++){
				innerList.add(new GridSquare<E>(x,y));
			}
			grid.add(innerList);
		}
		return grid;
	}
	
	private void propulateItemToSquareMap() {
		itemToSquareMap = new HashMap<>();
		for (GridSquare<E> square : getAllSquares()){
			itemToSquareMap.put(square.getItem(), square);
		}
		
	}
	
	private void createPaths() {
		GridPathBuilder<E> pathBuilder = new GridPathBuilder<E>(this);
		for (GridSquare<E> square : getAllSquares()){
			square.setPath(pathBuilder.buildPath(square));
		}
	}
	
	public GridSquare<E> getSquareRelativeTO(GridSquare<E> square, int xOffset, int yOffset){
		int x = square.getX() + xOffset;
		int y = square.getY() + yOffset;
		return getSquareAt(x, y);
	}
	
	public E getNeighbor(E item, GridDirection direction){
		GridSquare<E> square = getSquareOf(item);
		int x = square.getX() + direction.getOffsetX();
		int y = square.getY() + direction.getOffsetY();
		return getItemAt(x, y);
	}
	
	public GridSquare<E> getSquareAt(int x, int y){
		if (isValidLocation(x, y)){
			return grid.get(x).get(y);
		}
		return null;
	}
	
	public E getItemAt(int x, int y){
		if (isValidLocation(x, y)){
			return grid.get(x).get(y).getItem();
		}
		return null;
	}
	
	public void setAt(E item, int x, int y){
		if (isValidLocation(x, y)){
			GridSquare<E> square = grid.get(x).get(y);
			square.setItem(item);
			itemToSquareMap.put(item, square);
		}
	}
	
	public void setItem(GridSquare<E> square, E item){
		if (isValidSquare(square)){
			square.setItem(item);
			itemToSquareMap.put(item, square);
		}
	}
	
	public ArrayList<GridSquare<E>> getAllSquares(){
		ArrayList<GridSquare<E>> items = new ArrayList<GridSquare<E>>();
		for (ArrayList<GridSquare<E>> innerList : grid){
			items.addAll(innerList);
		}
		return items;
	}
	
	public ArrayList<E> getAllItems(){
		ArrayList<E> items = new ArrayList<>();
		for (ArrayList<GridSquare<E>> innerList : grid){
			items.addAll(GridSquare.getAllItems(innerList));
		}
		return items;
	}

	private boolean isValidLocation(int x, int y) {
		return x >= 0 && x < grid.size()
			&& y >= 0 && y < grid.size();
	}
	
	private boolean isValidSquare(GridSquare<E> square) {
		return square == getSquareAt(square.getX(), square.getY());
	}

	
	/**
	 * Returns the size of one dimension of the grid. The total items in this grid will be the square of this number
	 * @return
	 */
	public int getSizeInOneDimension(){
		return grid.size();
	}
	
	//TODO Could reuse a visitor pattern to make finding surrounding squares and items share more code / be more optimal?
	public ArrayList<GridSquare<E>> findAllSurroundingSquares(GridSquare<E> origin, int area){
		ArrayList<GridSquare<E>> squares = new ArrayList<>();
		for (int xOffset = -area; xOffset <=area; xOffset++) {
			int diag = area - Math.abs(xOffset);
			for (int yOffset = -diag; yOffset <= diag; yOffset++) {
				
				GridSquare<E> square = getSquareRelativeTO(origin, xOffset, yOffset);
				if (square != null){
					squares.add(square);
				}
			}
		}
		return squares;
	}
	
	public ArrayList<GridSquare<E>> findSurroundingRingOfSquares(GridSquare<E> origin, int area){
		ArrayList<GridSquare<E>> squares = new ArrayList<>();
		if (area != 0){
			for (int xOffset = -area; xOffset <=area; xOffset++) {
				int yOffset = area - Math.abs(xOffset);
				
				GridSquare<E> square = getSquareRelativeTO(origin, xOffset, yOffset);
				if (square != null){
					squares.add(square);
				}
				
				if (yOffset != 0){
					yOffset = -yOffset;
					square = getSquareRelativeTO(origin, xOffset, yOffset);
					if (square != null){
						squares.add(square);
					}
				}
			}
		}
		
		return squares;
	}
	
	public ArrayList<E> findSurroundingRingOfItems(GridSquare<E> origin, int area){
		ArrayList<E> items = new ArrayList<>();
		ArrayList<GridSquare<E>> squares = findSurroundingRingOfSquares(origin, area);
		for (GridSquare<E> square : squares) {
			items.add(square.getItem());
		}
		return items;
	}
	
	public ArrayList<E> findAllSurroundingItems(GridSquare<E> origin, int area){
		ArrayList<E> items = new ArrayList<>();
		for (GridSquare<E> square : findAllSurroundingSquares(origin, area)) {
			items.add(square.getItem());
		}
		return items;
	}
	
	public ArrayList<E> findAllSurroundingItemsThatAreValidForPathing(GridSquare<E> origin, int area){
		ArrayList<E> items = new ArrayList<>();
		for (GridSquare<E> square : findAllSurroundingSquares(origin, area)) {
			E item = square.getItem();
			if (item != null && PathingType.includeInPath(item.getPathingType())){
				items.add(square.getItem());
			}
		}
		return items;
	}
	
	public GridSquare<E> getSquareOf(E item){
		if (itemToSquareMap.containsKey(item)){
			return itemToSquareMap.get(item);
		}
		return null;
	}
	

}
