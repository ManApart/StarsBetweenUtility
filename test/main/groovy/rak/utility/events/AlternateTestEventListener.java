package rak.utility.events;

public class AlternateTestEventListener implements EventListener<TestEvent>{
	private boolean handleWasCalled;
	
	@Override
	public void handle(TestEvent event) {
		handleTestData(event.getTestData());
	}

	private void handleTestData(String testData) {
		handleWasCalled = true;
	}

}
