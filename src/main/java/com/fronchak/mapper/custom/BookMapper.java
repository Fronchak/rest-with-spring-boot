package com.fronchak.mapper.custom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.data.vo.v1.BookInputVO;
import com.fronchak.data.vo.v1.BookOutputVO;
import com.fronchak.models.entities.Book;

@Service
public class BookMapper {

	public Book convertInputVoToEntity(BookInputVO vo) {
		Book entity = new Book();
		entity.setTitle(vo.getTitle());
		entity.setAuthor(vo.getAuthor());
		entity.setLauchDate(vo.getLauchDate());
		entity.setPrice(vo.getPrice());
		return entity;
	}
	
	public BookOutputVO convertEntityToOutputVo(Book entity) {
		BookOutputVO vo = new BookOutputVO();
		vo.setId(entity.getId());
		vo.setAuthor(entity.getAuthor());
		vo.setTitle(entity.getTitle());
		vo.setLauchDate(entity.getLauchDate());
		vo.setPrice(entity.getPrice());
		return vo;
	}
	
	public List<BookOutputVO> convertEntityListToOutputVoList(List<Book> entityList) {
		return entityList.stream()
				.map(entity -> convertEntityToOutputVo(entity))
				.collect(Collectors.toList());	
	}
	
	public Book convertOutputVoToEntity(BookOutputVO vo) {
		Book entity = convertInputVoToEntity(vo);
		entity.setId(vo.getId());
		return entity;
	}
}
