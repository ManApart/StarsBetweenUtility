package rak.utility.interpolater;

public class KeyFrameTestImpl extends KeyFrame<KeyFrameTestImpl>{
	private int position;

	public KeyFrameTestImpl(int position){
		this.position = position;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public KeyFrameTestImpl createFrameByBlendingWith(KeyFrameTestImpl other, int blendPosition, float blendPercent) {
		return null;
	}


}
