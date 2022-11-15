package com.fronchak.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.fronchak.data.vo.v2.PersonVOV2;
import com.fronchak.models.entities.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGenre(person.getGenre());
		vo.setBirthDay(new Date());
		return vo;
	}
	
	public Person convertVOToEntity(PersonVOV2 vo) {
		Person person = new Person();
		person.setId(vo.getId());
		person.setFirstName(vo.getFirstName());
		person.setLastName(vo.getLastName());
		person.setGenre(vo.getGenre());
		person.setAddress(vo.getAddress());
		return person;
	}
}
