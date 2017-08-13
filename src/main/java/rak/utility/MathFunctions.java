package rak.utility;

import java.util.Random;

public class MathFunctions {
	private static Random generator = new Random(533300);
	
	public static int randRange(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
	
	public static int randRange(long seed, int min, int max) {
		return min + (int) (rand(seed) / (double) 100 * ((max - min) + 1));
	}
	
	public static int rand(long seed) {
		generator.setSeed(100 + seed * 1000);
		return (int) (generator.nextDouble() * (100));
	}
	
	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public static float clamp(float val, float min, float max) {
		return Math.max(min, Math.min(max, val));
	}
	
	public static double clamp(double val, double min, double max) {
		return Math.max(min, Math.min(max, val));
	}
	
	/**
	 * @return a float between -1 and 1
	 */
	public static float getPercent(int amount, int total){
		float center = total/2;
		float percent = (amount-center) / center;
		return percent;
	}

	public static double distanceBetween(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
	}
	
	/**
	 * Returns true if a random number returns a greater percent than the input percent 
	 */
	public static boolean probability(float chance){
		return generator.nextInt() > (chance*100);
	}
	

}
