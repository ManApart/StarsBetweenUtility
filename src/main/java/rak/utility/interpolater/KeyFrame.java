package rak.utility.interpolater;

public abstract class KeyFrame<E> implements Comparable<KeyFrame<E>>{
	
	public abstract int getPosition();
	public abstract E createFrameByBlendingWith(E other, int blendPosition, float blendPercent);
	
	@Override
	public String toString() {
		return "Key: " + getPosition();
	}
	
	@Override
	public int compareTo(KeyFrame<E> o) {
		int i = -1;
		if (o != null){
			if (this.getPosition() > o.getPosition()){
				i = 1;
			} else if (this.getPosition() < o.getPosition()){
				i = -1;
			} else {
				i = 0;
			}
		}
		return i;
	}

}
