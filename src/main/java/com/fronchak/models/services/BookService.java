package com.fronchak.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.exceptions.ResourceNotFoundException;
import com.fronchak.models.entities.Book;
import com.fronchak.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	public Book save(Book book) {
		return repository.save(book);
	}
	
	public Book findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("There is no book with this id registed"));
	}
}
