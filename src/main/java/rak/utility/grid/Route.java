package rak.utility.grid;

import java.util.ArrayList;

public class Route<E extends GridItem> {
	private GridSquare<E> source;
	private ArrayList<GridSquare<E>> steps = new ArrayList<>();
	
	public Route(GridSquare<E> source) {
		this.source = source;
		addStep(source);
	}
	
	@Override
	public String toString(){
		return "Route of " + source + " contains " + steps.size() + " steps";
	}

	public void addStep(GridSquare<E> step) {
		steps.add(step);
		
	}
	
	public GridSquare<E> getLastStep(){
		return steps.get(steps.size()-1);
	}
	
	public ArrayList<GridSquare<E>> getSteps(){
		return new ArrayList<>(steps);
	}
	
	public GridSquare<E> getSource(){
		return source;
	}

}
