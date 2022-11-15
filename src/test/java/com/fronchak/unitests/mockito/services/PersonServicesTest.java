package com.fronchak.unitests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fronchak.data.vo.v1.PersonVO;
import com.fronchak.exceptions.RequiredObjectIsNullException;
import com.fronchak.models.entities.Person;
import com.fronchak.models.services.PersonServices;
import com.fronchak.repositories.PersonRepository;
import com.fronchak.unitests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	private PersonRepository repository;
	
	@BeforeEach
	void setUp() {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		Person person = input.mockEntity();
		when(repository.findById(0L)).thenReturn(Optional.of(person));
		PersonVO vo = service.findById(0L);
		assertNotNull(vo);
		assertNotNull(vo.getPersonId());
		assertTrue(vo.toString().contains("links: [</person/0>;rel=\"self\"]"));
		assertEquals("First Name Test 0", vo.getFirstName());
		assertEquals("Last Name Test 0", vo.getLastName());
		assertEquals("Address Test 0", vo.getAddress());
		assertEquals("Male", vo.getGenre());
	}
	
	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setPersonId(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		PersonVO result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("First Name Test 1", result.getFirstName());
		assertEquals("Last Name Test 1", result.getLastName());
		assertEquals("Address Test 1", result.getAddress());
		assertEquals("Female", result.getGenre());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		assertEquals("It is not allowed to save a null object!", exception.getMessage());
	}
	
	@Test
	void testUpdate() {
		Person entity = input.mockEntity();
		Person persisted = input.mockEntity();
		
		PersonVO vo = input.mockVO();
		
		when(repository.findById(0L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		PersonVO result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/0>;rel=\"self\"]"));
		assertEquals("First Name Test 0", result.getFirstName());
		assertEquals("Last Name Test 0", result.getLastName());
		assertEquals("Address Test 0", result.getAddress());
		assertEquals("Male", result.getGenre());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		assertEquals("It is not allowed to save a null object!", exception.getMessage());
	}
	
	@Test
	void testDelete() {
		Person entity = input.mockEntity();
		when(repository.findById(0L)).thenReturn(Optional.of(entity));
		service.deleteById(0L);
		Mockito.verify(repository, Mockito.times(1)).delete(entity);
	}
	
	@Test
	void testAll() {
		List<Person> entityList = input.mockEntityList();
		when(repository.findAll()).thenReturn(entityList);
		List<PersonVO> resultList = service.findAll();
		assertNotNull(resultList);
		assertEquals(14, resultList.size());
		
		PersonVO personOne = resultList.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getPersonId());
		assertNotNull(personOne.getLinks());
		assertTrue(personOne.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("First Name Test 1", personOne.getFirstName());
		assertEquals("Last Name Test 1", personOne.getLastName());
		assertEquals("Address Test 1", personOne.getAddress());
		assertEquals("Female", personOne.getGenre());
	
		PersonVO personSix = resultList.get(6);
		assertNotNull(personSix);
		assertNotNull(personSix.getPersonId());
		assertNotNull(personSix.getLinks());
		assertTrue(personSix.toString().contains("links: [</person/6>;rel=\"self\"]"));
		assertEquals("First Name Test 6", personSix.getFirstName());
		assertEquals("Last Name Test 6", personSix.getLastName());
		assertEquals("Address Test 6", personSix.getAddress());
		assertEquals("Male", personSix.getGenre());
		
		
		PersonVO personEleven = resultList.get(11);
		assertNotNull(personEleven);
		assertNotNull(personEleven.getPersonId());
		assertNotNull(personEleven.getLinks());
		assertTrue(personEleven.toString().contains("links: [</person/11>;rel=\"self\"]"));
		assertEquals("First Name Test 11", personEleven.getFirstName());
		assertEquals("Last Name Test 11", personEleven.getLastName());
		assertEquals("Address Test 11", personEleven.getAddress());
		assertEquals("Female", personEleven.getGenre());
	}
}
