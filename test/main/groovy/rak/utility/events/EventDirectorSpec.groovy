package rak.utility.events

import spock.lang.Specification

class EventDirectorSpec extends Specification {

	def "test"(){
		given:
			TestEventListener listener = new TestEventListener()
			EventDirector.registerListener(listener)
			
			TestEvent event = new TestEvent()
			
		when:
			EventDirector.postEvent(event)
		then:
			assert listener.handleWasCalled == true
	}
}
