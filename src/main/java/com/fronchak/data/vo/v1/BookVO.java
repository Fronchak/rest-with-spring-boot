package com.fronchak.data.vo.v1;

import java.util.Date;

public class BookVO {

	private Integer id;
	private String author;
	private Date lauchDate;
	private Double price;
	private String title;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getLauchDate() {
		return lauchDate;
	}
	
	public void setLauchDate(Date lauchDate) {
		this.lauchDate = lauchDate;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
