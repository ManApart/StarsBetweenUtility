package rak.utility.interpolater

import spock.lang.Specification

class KeyFrameSpec extends Specification {
	
	def "Max returns proper keyfram"(){
		given:
			ArrayList<KeyFrameTestImpl> keyFrames = new ArrayList<>()
			for (int position : positions){
				keyFrames << new KeyFrameTestImpl(position)
			}
		when:
			KeyFrameTestImpl result = Collections.max(keyFrames)
		then:
			result.getPosition() == resultPosition
		where:
			startPosition	|	positions		|	resultPosition
			5				|	[0,100]			|	100
			-1				|	[0,100]			|	100
			5				|	[10,100]		|	100
			500				|	[10,100]		|	100
			5				|	[0,10,15,20,25,100]			|	100
	}
	
	def "Min returns proper keyfram"(){
		given:
			ArrayList<KeyFrameTestImpl> keyFrames = new ArrayList<>()
			for (int position : positions){
				keyFrames << new KeyFrameTestImpl(position)
			}
	when:
		KeyFrameTestImpl result = Collections.min(keyFrames)
		then:
			result.getPosition() == resultPosition
			where:
				startPosition	|	positions		|	resultPosition
				5				|	[0,100]			|	0
				-1				|	[0,100]			|	0
				5				|	[10,100]		|	10
				500				|	[10,100]		|	10
				5				|	[0,10,15,20,25,100]			|	0
	}

	
	
	
}
