package com.innogent.LibraryManagementSystem.DTO;

import org.springframework.stereotype.Component;

@Component
public class BookResponse {
    private Long id;
    private String title;
    private String authorName;
    private int stock;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}

