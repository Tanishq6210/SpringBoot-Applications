package com.int28minutes.mockito.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class SomeBusinessStubTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
//		dataServiceMock.retrieveAllData() => new int[] {1, 2, 15};
		
		//When this method "retrieveAllData" is called this new array will be returned. Now we don't have to make any new class implementing the interface to run the tests
		// Mockito will help in avoiding child classes
		// We are making the dependency available to the class
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1, 2, 15}); 
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findGreatestFromAllData();
		assertEquals(15, result);
	}
}
