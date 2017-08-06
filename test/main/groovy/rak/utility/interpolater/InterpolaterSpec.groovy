package rak.utility.interpolater

import spock.lang.Specification

class InterpolaterSpec extends Specification {
	

	def "blended frame returns proper progress"(){
		/*
		 * This method is only called with the proper before and after positions, and only if they don't match 
		 */
		given:
			Interpolater<KeyFrameTestImpl> interpolater = new Interpolater<KeyFrameTestImpl>()
			KeyFrameTestImpl keyFrameStart = new KeyFrameTestImpl(startPosition)
			KeyFrameTestImpl keyFrameEnd = new KeyFrameTestImpl(endPosition)
		when:
			float result = interpolater.getBlendRelativeToLowerBound(keyFrameStart, keyFrameEnd, position)
		then:
			result == resultPercent
		where:
			startPosition	|	endPosition	|	position	|	resultPercent
			0				|	100			|	50			|	0.5f
			0				|	100			|	100			|	0
			0				|	100			|	0			|	1
			10				|	20			|	15			|	0.5f
			0				|	50			|	25			|	0.5f
			10				|	20			|	50			|	0
			10				|	20			|	1			|	1
	}
	
	def "get next returns proper item"(){
		given:
			Interpolater<KeyFrameTestImpl> interpolater = new Interpolater<KeyFrameTestImpl>()
			for (int position : positions){
				interpolater.addKeyFrame(new KeyFrameTestImpl(position))
			}
		when:
			KeyFrameTestImpl result = interpolater.getNext(startPosition)
		then:
			result.getPosition() == resultPosition
		where:
			startPosition	|	positions		|	resultPosition
			5				|	[0,100]			|	100
			-1				|	[0,100]			|	0
			5				|	[10,100]		|	10
			500				|	[10,100]		|	100
	}
	
	def "get previous returns proper item"(){
		given:
			Interpolater<KeyFrameTestImpl> interpolater = new Interpolater<KeyFrameTestImpl>()
			for (int position : positions){
				interpolater.addKeyFrame(new KeyFrameTestImpl(position))
			}
		when:
			KeyFrameTestImpl result = interpolater.getPrevious(startPosition)
		then:
			result.getPosition() == resultPosition
			where:
				startPosition	|	positions		|	resultPosition
				5				|	[0,100]			|	0
				-1				|	[0,100]			|	0
				5				|	[10,100]		|	10
				500				|	[10,100]		|	100
	}

	
	
	
}
