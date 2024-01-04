package com.int28minutes.mockito.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void test() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10);
		assertEquals(10, listMock.size());
		// Irrespective of how many times I call this, it will stay 10
		assertEquals(10, listMock.size());
	}

	@Test
	void testSize_multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, listMock.size());
		// Since, there are two .thenReturns() therefore 1 time we will get 10 then we
		// will get 20, then it will keep returning 20 i.e. last value
		assertEquals(20, listMock.size());
	}

	@Test
	void testGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("Hello");
		assertEquals("Hello", listMock.get(0));
		// For any other index, it will return null
		assertEquals(null, listMock.get(1));
	}

	@Test
	void testGet_GenericParameter() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("Hello");
		assertEquals("Hello", listMock.get(0));
		// For any other index, it will still return "Hello"
		assertEquals(null, listMock.get(1));
	}
}
