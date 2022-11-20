package com.fronchak.unitests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fronchak.data.vo.v1.BookInputVO;
import com.fronchak.data.vo.v1.BookOutputVO;
import com.fronchak.mapper.custom.BookMapper;
import com.fronchak.models.entities.Book;
import com.fronchak.unitests.mapper.mocks.MockBook;

public class BookMapperTest {

	private MockBook inputObject;
	private BookMapper mapper;
	
	@BeforeEach
	void setUp() {
		inputObject = new MockBook();
		mapper = new BookMapper();
	}
	
	@Test
	void shouldMapperInputVoToEntity() {
		BookInputVO vo = inputObject.mockInputVo();
		Book result = mapper.convertInputVoToEntity(vo);
		assertEquals("Title Test 0", result.getTitle());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
	}
	
	@Test
	void shouldMapperEntityToOutputVo() {
		Book entity = inputObject.mockEntity();
		BookOutputVO result = mapper.convertEntityToOutputVo(entity);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
	}
	
	@Test
	void shouldMapperOutputVoToEntity() {
		BookOutputVO vo = inputObject.mockOutputVo();
		Book result = mapper.convertOutputVoToEntity(vo);
		assertEquals(0, result.getId());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
	}
	
	@Test
	void shouldMapperEntityListToOutputVoList() {
		List<Book> entityList = inputObject.mockEntityList();
		List<BookOutputVO> resultList = mapper.convertEntityListToOutputVoList(entityList);
		
		BookOutputVO resultZero = resultList.get(0);
		assertEquals(0, resultZero.getId());
		assertEquals("Title Test 0", resultZero.getTitle());
		assertEquals("Author Test 0", resultZero.getAuthor());
		assertEquals(new Date(0L), resultZero.getLauchDate());
		assertEquals(0.5, resultZero.getPrice());
		
		BookOutputVO resultOne = resultList.get(1);
		assertEquals(1, resultOne.getId());
		assertEquals("Title Test 1", resultOne.getTitle());
		assertEquals("Author Test 1", resultOne.getAuthor());
		assertEquals(new Date(1L), resultOne.getLauchDate());
		assertEquals(1.5, resultOne.getPrice());
		
		BookOutputVO resultFour = resultList.get(4);
		assertEquals(4, resultFour.getId());
		assertEquals("Title Test 4", resultFour.getTitle());
		assertEquals("Author Test 4", resultFour.getAuthor());
		assertEquals(new Date(4L), resultFour.getLauchDate());
		assertEquals(4.5, resultFour.getPrice());
	}
	
}
