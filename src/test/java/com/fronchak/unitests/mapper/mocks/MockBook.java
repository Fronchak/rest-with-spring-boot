package com.fronchak.unitests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fronchak.data.vo.v1.BookInputVO;
import com.fronchak.data.vo.v1.BookOutputVO;
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
	
	public List<Book> mockEntityList() {
		List<Book> bookList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			bookList.add(mockEntity(i));
		}
		return bookList;
	}
	
	public BookOutputVO mockOutputVo() {
		return mockOutputVo(0);
	}
	
	public BookOutputVO mockOutputVo(int number) {
		BookOutputVO vo = new BookOutputVO();
		vo.setAuthor("Author Test " + number);
		vo.setPrice(number + 0.5);
		vo.setTitle("Title Test " + number);
		vo.setLauchDate(new Date(number));
		vo.setId(number);
		return vo;
	}
	
	public BookInputVO mockInputVo() {
		return mockInputVo(0);
	}
	
	public BookInputVO  mockInputVo(int number) {
		BookInputVO vo = new BookInputVO();
		vo.setAuthor("Author Test " + number);
		vo.setPrice(number + 0.5);
		vo.setTitle("Title Test " + number);
		vo.setLauchDate(new Date(number));
		return vo;
	}
	
	public List<BookOutputVO> mockOutputVoList() {
		List<BookOutputVO> outputVoList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			outputVoList.add(mockOutputVo(i));
		}
		return outputVoList;
	}
}
