package rak.utility.interpolater;

import java.util.Collections;
import java.util.TreeMap;

import rak.utility.MathFunctions;

public class Interpolater<KeyFrameItem extends KeyFrame<KeyFrameItem>> {
	private TreeMap<Integer, KeyFrameItem> keyFrames = new TreeMap<>();
	
	public void addKeyFrame(KeyFrameItem keyFrame){
		keyFrames.put(keyFrame.getPosition(), keyFrame);
	}
	
	public KeyFrameItem getInterpolatedValue(int position){
		KeyFrameItem before = getPrevious(position);
		KeyFrameItem after = getNext(position);
		
		//If we're already out of range, return the last on the list
		if (before == after){
			return before;
		}
		
		float blendPercent = getBlendRelativeToLowerBound(before, after, position);
		KeyFrameItem blendedFrame = before.createFrameByBlendingWith(after, position, blendPercent);		
		
		return blendedFrame;
	}
	
	private KeyFrameItem getNext(int position){
		if (keyFrames.ceilingEntry(position) != null){
			return keyFrames.ceilingEntry(position).getValue();
		}
		return getMaxValue();
	}

	private KeyFrameItem getMaxValue() {
		return Collections.max(keyFrames.values());
	}
	
	private KeyFrameItem getPrevious(int position){
		if (keyFrames.floorEntry(position) != null){
			return keyFrames.floorEntry(position).getValue();
		}
		return getMinValue();
	}

	private KeyFrameItem getMinValue() {
		return Collections.min(keyFrames.values());
	}
	
	private float getBlendRelativeToLowerBound(KeyFrameItem lowerBound, KeyFrameItem upperBound, int position) {
		float distanceBetweenBounds = upperBound.getPosition() - lowerBound.getPosition();
		int progress = position - lowerBound.getPosition();
		progress = (int) MathFunctions.clamp(progress, 0, distanceBetweenBounds);
		
		float percentRelativeToUpperBound = progress/distanceBetweenBounds;
		float percent = 1 - percentRelativeToUpperBound;
//		System.out.println("B: " + before.getPosition() + ", P: " + position + ", A: " + after.getPosition() + ", P: " + percent);
		return percent;
	}

}
