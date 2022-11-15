package com.fronchak.unitests.mapper;

import org.junit.jupiter.api.BeforeEach;

import com.fronchak.unitests.mapper.mocks.MockBook;

public class BookMapperTest {

	private MockBook inputObject;
	
	@BeforeEach
	void setUp() {
		inputObject = new MockBook();
	}
	
}
