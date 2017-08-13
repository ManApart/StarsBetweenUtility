package rak.utility.events

import spock.lang.Specification

class EventDirectorSpec extends Specification {

	def "A posted event is sent to a listener"(){
		given:
			TestEventListener listener = new TestEventListener()
			EventDirector.registerListener(listener)
			
			TestEvent event = new TestEvent()
			
		when:
			EventDirector.postEvent(event)
		then:
			assert listener.handleWasCalled == true
	}
	
	def "Two listeners are both called for the same event"(){
		given:
			TestEventListener listener = new TestEventListener()
			AlternateTestEventListener alternateListener = new AlternateTestEventListener()
			
			EventDirector.registerListener(listener)
			EventDirector.registerListener(alternateListener)
			
			TestEvent event = new TestEvent()
			
		when:
			EventDirector.postEvent(event)
		then:
			assert listener.handleWasCalled == true
			assert alternateListener.handleWasCalled == true
	}
}
