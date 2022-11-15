package com.fronchak.unitests.mapper.mocks;

import java.util.Date;

import com.fronchak.data.vo.v1.BookVO;
import com.fronchak.models.entities.Book;

public class MockBook {

	public Book mockEntity() {
		return mockEntity(0);
	}
	
	public Book mockEntity(int number) {
		Book book = new Book();
		book.setId(number);
		book.setAuthor("Author Test " + number);
		book.setPrice(number + 0.5);
		book.setTitle("Title Test " + number);
		book.setLauchDate(new Date(number));
		return book;
	}
	
	public Book mockEntityWithoutId() {
		return mockEntityWithoutId(0);
	}
	
	public Book mockEntityWithoutId(int number) {
		Book book = mockEntity(number);
		book.setId(null);
		return book;
	}
	
	public BookVO mockVo() {
		return mockVo(0);
	}
	
	public BookVO mockVo(int number) {
		BookVO vo = new BookVO();
		vo.setId(number);
		vo.setAuthor("Author Test " + number);
		vo.setPrice(number + 0.5);
		vo.setTitle("Title Test " + number);
		vo.setLauchDate(new Date(number));
		return vo;
	}
}
