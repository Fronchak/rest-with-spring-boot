package com.fronchak.unitests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.data.vo.v1.PersonVO;
import com.fronchak.models.entities.Person;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public Person mockEntity(Integer number) {
		Person person = new Person();
		person.setId(number.longValue());
		person.setFirstName("First Name Test " + number);
		person.setLastName("Last Name Test " + number);
		person.setAddress("Address Test " + number);
		person.setGenre(((number % 2) == 0) ? "Male" : "Female");
		return person;
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setPersonId(number.longValue());
		person.setFirstName("First Name Test " + number);
		person.setLastName("Last Name Test " + number);
		person.setAddress("Address Test " + number);
		person.setGenre(((number % 2) == 0) ? "Male" : "Female");
		return person;
	}
	
	public List<Person> mockEntityList() {
		List<Person> people = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			people.add(mockEntity(i));
		}
		return people;
	}
	
	public List<PersonVO> mockVOList() {
		List<PersonVO> people = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			people.add(mockVO(i));
		}
		return people;
	}
	
}
