package com.innogent.LibraryManagementSystem.DTO;

import org.springframework.stereotype.Component;

@Component
public class BookRequest {
    private String title;
    private Long authorId;
    private int stock;
    
    public BookRequest() {}
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	  
}


