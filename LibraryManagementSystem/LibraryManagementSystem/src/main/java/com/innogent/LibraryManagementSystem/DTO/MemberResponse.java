package com.innogent.LibraryManagementSystem.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private List<String> borrowedBookTitles;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrowedBookTitles() {
        return borrowedBookTitles;
    }
    public void setBorrowedBookTitles(List<String> borrowedBookTitles) {
        this.borrowedBookTitles = borrowedBookTitles;
    }
}
