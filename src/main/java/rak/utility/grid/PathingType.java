package rak.utility.grid;

/**
 *	Blocked types will not be added to a path 
 *	Open types will be added to a path along with its valid neighbors
 *	Destination types will be added to a path, but the path will not search further from that tile
 */
public enum PathingType {
	BLOCKED,
	OPEN,
	DESTINATION;
	
	
	public static boolean includeInPath(PathingType type){
		return type == PathingType.OPEN || type == DESTINATION;
	}
}
