package rak.utility.grid;

import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;

public class RouteBuilder<E extends GridItem> {
	private GridPath<E> path;
	private Grid<E> grid;
	private ArrayList<Route<E>> routes;
	
	public RouteBuilder(Grid<E> grid, GridPath<E> path){
		this.grid = grid;
		this.path = path;
	}

	public void buildRoutes() {
		createRoutes();
//		printRoutes();
	}
	
	private void createRoutes(){
		routes = new ArrayList<>();
		
//		if (path.getSource().getX() == 4 && path.getSource().getY() == 2 && path.getSource().getItem() != null){
//			System.out.println("station");
//		}
		
		for (int i=1; i<=path.getMaxDistance(); i++){
			ArrayList<GridSquare<E>> previousSquares = path.getSquaresAt(i-1);
			ArrayList<GridSquare<E>> currentSquares = path.getSquaresAt(i);
			
			for (GridSquare<E> previousSquare : previousSquares){
				ArrayList<GridSquare<E>> neighbors = (ArrayList<GridSquare<E>>) CollectionUtils.intersection(currentSquares, grid.findAllSurroundingSquares(previousSquare, 1));
				
				if (neighbors.size() == 1){
					Route<E> route = getRouteByLastStep(previousSquare);
					route.addStep(neighbors.get(0));
					//if more than one route, create new routes
				} else {
					for (GridSquare<E> neighbor : neighbors){
						Route<E> nextRoute = new Route<E>(previousSquare);
						nextRoute.addStep(neighbor);
						routes.add(nextRoute);
					}
				}
			}
		}
	}
	
	public ArrayList<Route<E>> getRoutes(){
		return new ArrayList<>(routes);
	}
	
	private Route<E> getRouteByLastStep(GridSquare<E> step){
		for (Route<E> route : routes){
			if (route.getLastStep() == step){
				return route;
			}
		}
		return new Route<E>(step);
	}
	
	private void printRoutes(){
		for (Route<E> route : routes){
			System.out.println("\n---" + route);
			for (int i=0; i < route.getSteps().size(); i++){
				GridSquare<E> step = route.getSteps().get(i);
				System.out.println(i + ") " + step);
			}
		}
	}

}
