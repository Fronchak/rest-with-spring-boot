package com.fronchak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fronchak.data.vo.v1.PersonVO;
import com.fronchak.data.vo.v2.PersonVOV2;
import com.fronchak.models.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(value = "/{id}", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE,
			"application/x-yaml" 
		})
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@GetMapping(produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE,
			"application/x-yaml" 
		})
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	@PostMapping(value = "/v2", 
			consumes = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE,
					"application/x-yaml"  
				}, 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE,
					"application/x-yaml" 
				})
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			"application/x-yaml"}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
					"application/x-yaml"})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			"application/x-yaml"}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
					"application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
