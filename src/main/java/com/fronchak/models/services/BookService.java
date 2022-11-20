package com.fronchak.models.services;

import java.util.List;

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
	
	public Book update(Book book) {
		repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("There is no book with thhis id registed"));
		return repository.save(book);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public List<Book> findAll() {
		return repository.findAll();
	}
}
