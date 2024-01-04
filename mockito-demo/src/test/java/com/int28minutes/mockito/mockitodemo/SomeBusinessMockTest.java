package com.int28minutes.mockito.mockitodemo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class SomeBusinessMockTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(new DataServiceStub());
		int result = businessImpl.findGreatestFromAllData();
		assertEquals(15, result);
	}
}

class DataServiceStub implements DataService {

	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[] { 4, 6, 15 };
	}

}