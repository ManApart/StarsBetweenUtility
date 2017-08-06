package rak.utility;

public class DebugTimer {
	private long startTime;
	private long intervalTime;
	
	public DebugTimer() {
	}

	/**
	 * Start a new timer at the current system time.
	 * Display a message including the input message.
	 */
	public void start(String message){
		long now = getCurrentTime();
		startTime = now;
		intervalTime = now;
		printMessage("Starting: " + message);
		
	}

	/**
	 * Display a message that indicates the time since the last interval.
	 */
	public void interval(String message){
		long now = getCurrentTime();
		long elapsed = getElapsed(intervalTime, now);
		intervalTime = now;
		
		printMessage(elapsed + " elapsed since interval: " + message);
	}
	
	/**
	 * Display a message that indicates the time since the timer started
	 */
	public void elapsed(String message){
		long now = getCurrentTime();
		long elapsed = getElapsed(startTime, now);
		
		printMessage(elapsed + " elapsed since start: " + message);
	}
	
	private long getCurrentTime() {
		long now = System.currentTimeMillis();
		
		return now;
	}
	
	private long getElapsed(long startTime, long endTime) {
		return endTime-startTime;
	}
	
	private void printMessage(String message) {
		System.out.println(message);
	}
	
}
