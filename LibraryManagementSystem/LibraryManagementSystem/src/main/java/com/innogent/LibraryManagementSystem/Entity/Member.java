package com.innogent.LibraryManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String email;

    @ManyToMany
    @JoinTable(
        name = "member_books",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> borrowedBooks = new HashSet<>();

    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(Set<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }
	
    @Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", borrowedBooks=" + borrowedBooks + "]";
	}

}
