package rak.utility.attributeGrouper;

public class IntAttribute implements Attribute<IntAttribute>{
	private String key;
	private int value;
	private int maxDistance;
	
	public IntAttribute(String key, int value, int maxDistance) {
		this.key = key;
		this.value = value;
		this.maxDistance = maxDistance;
	}
	
	@Override
	public int getDistance(IntAttribute other) {
		int otherValue = other != null ? other.value : 0;
		return Math.abs(value - otherValue);
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public boolean canCompareWith(IntAttribute other) {
		if (other != null){
			if (aMinimumDistanceIsEnforced(other)){
				int max = getMoreExclusiveMaxDistance(other);
				if (distanceIsGreaterThanLimit(other, max)){
					return false;
				}
			}
		}
		return true;
	}

	private boolean aMinimumDistanceIsEnforced(IntAttribute other) {
		return maxDistance != 0 && other.maxDistance != 0;
	}

	private int getMoreExclusiveMaxDistance(IntAttribute other) {
		if (maxDistance == 0){
			return other.maxDistance;
		} else if (other.maxDistance == 0){
			return maxDistance;
		} else {
			return Math.min(maxDistance, other.maxDistance);
		}
	}
	
	private boolean distanceIsGreaterThanLimit(IntAttribute other, int max) {
		return getDistance(other) > max;
	}
	
}
