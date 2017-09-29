package rak.utility.grid;

public enum GridIdItemTypes implements GridItemBuilder<GridItemWithId>{
	A1(1),
	A2(2),
	A3(3),
	A4(4),
	A5(5),
	A6(6),
	A7(7),
	A8(8),
	A9(9);
	
	private int id;
	
	private GridIdItemTypes(int id) {
		this.id = id;
	}

	@Override
	public GridItemWithId buildSquare() {
		return new GridItemWithId(id);
	}
	
	public static final GridIdItemTypes[][] GRID = {{A1,A2,A3}, 
													{A4,A5,A6}, 
													{A7,A8,A9}};

}
