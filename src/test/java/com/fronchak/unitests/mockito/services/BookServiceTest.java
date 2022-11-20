package com.fronchak.unitests.mockito.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fronchak.exceptions.ResourceNotFoundException;
import com.fronchak.models.entities.Book;
import com.fronchak.models.services.BookService;
import com.fronchak.repositories.BookRepository;
import com.fronchak.unitests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	private MockBook inputObject;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	private BookRepository repository;
	
	@BeforeEach
	void setUp() {
		inputObject = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void shouldSaveBook() {
		Book book = inputObject.mockEntityWithoutId();
		Book persisted = inputObject.mockEntity();
		when(repository.save(book)).thenReturn(persisted);
		
		Book result = service.save(book);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals(0.5, result.getPrice());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
	}
	
	@Test
	void shouldReturnBookById() {
		Book persisted = inputObject.mockEntity();
		when(repository.findById(0)).thenReturn(Optional.of(persisted));
		Book result = service.findById(0);
		assertEquals(0, result.getId());
		assertEquals("Author Test 0", result.getAuthor());
		assertEquals(0.5, result.getPrice());
		assertEquals("Title Test 0", result.getTitle());
		assertEquals(new Date(0L), result.getLauchDate());
	}
	
	@Test
	void shouldThrowResourceNotFoundExceptionWhenEntityDoesNotExist() {
		when(repository.findById(0)).thenReturn(Optional.empty());
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(0);
		});
		assertEquals("There is no book with this id registed", exception.getMessage());
	}
	
	@Test
	void shouldUpdateAndReturnBook() {
		Book book = inputObject.mockEntity();
		Book persisted = inputObject.mockEntity();
		when(repository.findById(0)).thenReturn(Optional.of(book));
		when(repository.save(book)).thenReturn(persisted);
		
		Book result = service.update(book);
		assertEquals(0, result.getId());
	}
	
	@Test
	void shouldThrowResourceNotFoundExceptionWhenTryToUpdateOneNonexistentBook() {
		Book book = inputObject.mockEntity();
		when(repository.findById(0)).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				() -> service.update(book));
		assertEquals("There is no book with thhis id registed", exception.getMessage());
	}
	
	@Test
	void shouldDeleteBook() {
		service.deleteById(0);
		Mockito.verify(repository, Mockito.times(1)).deleteById(0);
	}
	
	@Test
	void shouldReturnBookList() {
		List<Book> entityList = inputObject.mockEntityList();
		when(repository.findAll()).thenReturn(entityList);
		
		List<Book> resultList = service.findAll();
		
		Book bookZero = resultList.get(0);
		assertEquals(0, bookZero.getId());
		assertEquals("Author Test 0", bookZero.getAuthor());
		assertEquals(0.5, bookZero.getPrice());
		assertEquals("Title Test 0", bookZero.getTitle());
		assertEquals(new Date(0L), bookZero.getLauchDate());
		
		Book bookThree = resultList.get(3);
		assertEquals(3, bookThree.getId());
		assertEquals("Author Test 3", bookThree.getAuthor());
		assertEquals(3.5, bookThree.getPrice());
		assertEquals("Title Test 3", bookThree.getTitle());
		assertEquals(new Date(3L), bookThree.getLauchDate());
		
		Book bookFour = resultList.get(4);
		assertEquals(4, bookFour.getId());
		assertEquals("Author Test 4", bookFour.getAuthor());
		assertEquals(4.5, bookFour.getPrice());
		assertEquals("Title Test 4", bookFour.getTitle());
		assertEquals(new Date(4L), bookFour.getLauchDate());
	}
}
