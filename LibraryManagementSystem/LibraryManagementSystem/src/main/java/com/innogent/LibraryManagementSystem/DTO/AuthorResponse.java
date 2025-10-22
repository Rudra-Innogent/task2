package com.innogent.LibraryManagementSystem.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AuthorResponse {
    private Long id;
    private String name;
    private List<String> bookTitles;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getBookTitles() {
		return bookTitles;
	}
	public void setBookTitles(List<String> bookTitles) {
		this.bookTitles = bookTitles;
	}

}
