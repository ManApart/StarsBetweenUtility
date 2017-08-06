package rak.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Histogram {
	private HashMap<Integer, Integer> histogram = new HashMap<>();
	private int minValue;
	private int maxValue;
	
	public void add(int location){
		if (histogram.containsKey(location)){
			int newValue = 1 + histogram.get(location);
			histogram.put(location, newValue);
		} else {
			histogram.put(location, 1);
		}
	}
	
	public int getTotal(int location){
		if (histogram.containsKey(location)){
			return histogram.get(location);
		}
		System.out.println("Location " + location + " is out of bounds");
		return 0;
	}
	
	public int[] getTotals(){
		calculateMinAndMax();
		int size = maxValue-minValue + 1;
		int[] totals = new int[size];
		for (int i = 0; i < size; i++){
			totals[i] = getTotal(i+ minValue);
		}
		return totals;
	}
	
	private void calculateMinAndMax(){
		ArrayList<Integer> keys = new ArrayList<>(histogram.keySet());
		minValue = Collections.min(keys);
		maxValue = Collections.max(keys);
	}
	
	public int getMin(){
		calculateMinAndMax();
		return minValue;
	}
	
	public int getMax(){
		calculateMinAndMax();
		return maxValue;
	}

}
