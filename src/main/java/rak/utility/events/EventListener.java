package rak.utility.events;

public interface EventListener<E extends Event> {
	
	public abstract void handle(E event);
	
}
