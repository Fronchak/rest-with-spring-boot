package com.fronchak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fronchak.data.vo.v1.BookInputVO;
import com.fronchak.data.vo.v1.BookOutputVO;
import com.fronchak.mapper.custom.BookMapper;
import com.fronchak.models.entities.Book;
import com.fronchak.models.services.BookService;

@RestController	
@RequestMapping("/book")
public class BookController {

	@Autowired	
	private BookService service;
	
	@Autowired
	private BookMapper mapper;
	
	@PostMapping
	public BookOutputVO save(@RequestBody BookInputVO inputVo) {
		Book book = mapper.convertInputVoToEntity(inputVo);
		Book entity = service.save(book);
		BookOutputVO outputVO = mapper.convertEntityToOutputVo(entity);
		return outputVO;
	}
	
	@PutMapping
	public BookOutputVO update(@RequestBody BookOutputVO inputVo) {
		Book book = mapper.convertOutputVoToEntity(inputVo);
		Book entity = service.update(book);
		BookOutputVO outputVo = mapper.convertEntityToOutputVo(entity);
		return outputVo;
	}
	
	@GetMapping(value = "/{id}")
	public BookOutputVO findById(@PathVariable(name = "id") Integer id) {
		Book entity = service.findById(id);
		BookOutputVO outputVo = mapper.convertEntityToOutputVo(entity);
		return outputVo;
	}
	
	@GetMapping
	public List<BookOutputVO> findAll() {
		List<Book> entityList = service.findAll();
		List<BookOutputVO> outputVoList = mapper.convertEntityListToOutputVoList(entityList);
		return outputVoList;
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteById(@PathVariable(name = "id") Integer id) {
		service.deleteById(id);
	}
}
