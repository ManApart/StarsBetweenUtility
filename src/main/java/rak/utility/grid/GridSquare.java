package rak.utility.grid;

import java.util.ArrayList;

public class GridSquare<E extends GridItem> {
	private int x, y;
	private E item;
	private GridPath<E> path;
	
	public GridSquare(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public GridSquare(int x, int y, E item){
		this.x = x;
		this.y = y;
		this.item = item;
	}
	
	@Override
	public String toString() {
		String message = "Grid Square: x:" + x + ", y:" + y;
		if (item != null){
			message += ", item:" + item.getClass().getSimpleName();
		}
		return message;
	}
	
	protected void setItem(E item){
		this.item = item;
	}
	
	public E getItem(){
		return item;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isAt(int x, int y){
		return getX() == x && getY() == y;
	}

	public GridPath<E> getPath() {
		return path;
	}

	protected void setPath(GridPath<E> path) {
		this.path = path;
	}
	
	public static <E extends GridItem> ArrayList<E> getAllItems(ArrayList<GridSquare<E>> squares){
		ArrayList<E> items = new ArrayList<>();
		for (GridSquare<E> square : squares){
			E item = square.getItem();
			if (item != null){
				items.add(item);
			}
		}
		return items;
	}
	
	public static <E extends GridItem> ArrayList<GridPath<E>> getAllPaths(ArrayList<GridSquare<E>> squares){
		ArrayList<GridPath<E>> paths = new ArrayList<>();
		for (GridSquare<E> square : squares){
			GridPath<E> item = square.getPath();
			if (item != null){
				paths.add(item);
			}
		}
		return paths;
	}
	
}
