package rak.utility.attributeGrouper;

public interface Attribute <E extends Attribute<E>>{
	
	public String getKey();
	public boolean canCompareWith(E other);
	public int getDistance(E other);

}
