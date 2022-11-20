package com.fronchak.models.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.fronchak.controllers.PersonController;
import com.fronchak.data.vo.v1.PersonVO;
import com.fronchak.data.vo.v2.PersonVOV2;
import com.fronchak.exceptions.RequiredObjectIsNullException;
import com.fronchak.exceptions.ResourceNotFoundException;
import com.fronchak.mapper.DozerMapper;
import com.fronchak.mapper.custom.PersonMapper;
import com.fronchak.models.entities.Person;
import com.fronchak.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public PersonVO findById(Long id) {
		logger.info("Finding a person");
		Person person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		List<PersonVO> people = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		people.forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getPersonId())).withSelfRel()));
		return people;
	}
	
	public PersonVO create(PersonVO personVO) {
		
		if(personVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person.");
		
		Person person = DozerMapper.parseObject(personVO, Person.class);
		Person entity = repository.save(person);
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPersonId())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVO) {
		logger.info("Creating one person using V2");
		Person person = mapper.convertVOToEntity(personVO);
		Person entity = repository.save(person);
		PersonVOV2 vo = mapper.convertEntityToVO(entity);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating person.");
		Person test = DozerMapper.parseObject(person, Person.class);
		Person entity = repository.findById(person.getPersonId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGenre(person.getGenre());
		//é possivel passar o objeto que não foi buscado da database
		//quando se quer atualizar o mesmo
		Person entitySaved = repository.save(test);
		PersonVO vo = DozerMapper.parseObject(entitySaved, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPersonId())).withSelfRel());
		return vo;
	}
	
	public void deleteById(Long id) {
		logger.info("Deleting one person.");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
		
	}
}
