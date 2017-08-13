package rak.utility.events;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDirector {
	private static HashMap<Class<?>, ArrayList<EventListener<?>>> listenerMap = new HashMap<>();

	public static <E extends Event> void registerListener(EventListener<E> listener){
		Class<E> listenerClass = getListendForClass(listener);
		if (!listenerMap.containsKey(listenerClass)){
			listenerMap.put(listenerClass, new ArrayList<>());
		}
		listenerMap.get(listenerClass).add(listener);
	}
	
	@SuppressWarnings("unchecked")
	public static <E extends Event> void postEvent(E event){
		Class<E> eventClass = (Class<E>) event.getClass();
		if (listenerMap.containsKey(eventClass)){
			List<EventListener<?>> listenerList = listenerMap.get(eventClass);
			for (int i=0; i<listenerList.size(); i++){
				EventListener<E> listener = (EventListener<E>) listenerList.get(i);
				listener.handle(event);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <E extends Event> Class<E> getListendForClass(EventListener<E> listener){
		return (Class<E>) ((ParameterizedType) listener.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
	}
}
