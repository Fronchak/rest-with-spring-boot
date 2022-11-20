package com.fronchak.unitests.mockito.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fronchak.controllers.BookController;
import com.fronchak.data.vo.v1.BookInputVO;
import com.fronchak.data.vo.v1.BookOutputVO;
import com.fronchak.mapper.custom.BookMapper;
import com.fronchak.models.entities.Book;
import com.fronchak.models.services.BookService;
import com.fronchak.unitests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
	
	private MockBook inputObject;
	
	@InjectMocks
	private BookController controller;
	
	@Mock
	private BookService service;
	
	@Mock
	private BookMapper mapper;
	
	@BeforeEach
	void setUp() {
		inputObject = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void shouldSaveBook() {
		BookInputVO inputVo = inputObject.mockInputVo();
		Book book = inputObject.mockEntityWithoutId();
		Book persisted = inputObject.mockEntity();
		BookOutputVO outputVo = inputObject.mockOutputVo();
		
		when(mapper.convertInputVoToEntity(inputVo)).thenReturn(book);
		when(service.save(book)).thenReturn(persisted);
		when(mapper.convertEntityToOutputVo(persisted)).thenReturn(outputVo);
		
		BookOutputVO result = controller.save(inputVo);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
	}
	
	@Test
	void shouldUpdateBook() {
		BookOutputVO inputVo = inputObject.mockOutputVo();
		Book book = inputObject.mockEntity();
		BookOutputVO outputVo = inputObject.mockOutputVo();
		
		when(mapper.convertOutputVoToEntity(inputVo)).thenReturn(book);
		when(service.update(book)).thenReturn(book);
		when(mapper.convertEntityToOutputVo(book)).thenReturn(outputVo);
		
		BookOutputVO result = controller.update(inputVo);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
	}
	
	@Test
	void shouldFindBookById() {
		Book entity = inputObject.mockEntity();
		BookOutputVO outputVo = inputObject.mockOutputVo();
		
		when(service.findById(0)).thenReturn(entity);
		when(mapper.convertEntityToOutputVo(entity)).thenReturn(outputVo);
		
		BookOutputVO result = controller.findById(0);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
		assertEquals(0.5, result.getPrice());
		verify(service, times(1)).findById(0);
		verify(mapper, times(1)).convertEntityToOutputVo(entity);
	}
	
	@Test
	void shouldReturnAllBooks() {
		List<Book> entityList = inputObject.mockEntityList();
		List<BookOutputVO> outputVoList = inputObject.mockOutputVoList();
		
		when(service.findAll()).thenReturn(entityList);
		when(mapper.convertEntityListToOutputVoList(entityList)).thenReturn(outputVoList);
		
		List<BookOutputVO> result = controller.findAll();
		
		BookOutputVO bookZero = result.get(0);
		assertEquals(0, bookZero.getId());
		assertEquals("Author Test 0", bookZero.getAuthor());
		assertEquals("Title Test 0", bookZero.getTitle());
		assertEquals(new Date(0L), bookZero.getLauchDate());
		assertEquals(0.5, bookZero.getPrice());
		
		BookOutputVO bookOne = result.get(1);
		assertEquals(1, bookOne.getId());
		assertEquals("Author Test 1", bookOne.getAuthor());
		assertEquals("Title Test 1", bookOne.getTitle());
		assertEquals(new Date(1L), bookOne.getLauchDate());
		assertEquals(1.5, bookOne.getPrice());
	}
	
	@Test
	void shouldDeleteBook() {
		controller.deleteById(0);
		verify(service, times(1)).deleteById(0);
	}
}
