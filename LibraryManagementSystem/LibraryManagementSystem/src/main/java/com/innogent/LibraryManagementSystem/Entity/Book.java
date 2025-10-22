package com.innogent.LibraryManagementSystem.Entity;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<Member> members;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public Set<Member> getMembers() { return members; }
    public void setMembers(Set<Member> members) { this.members = members; }
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", stock=" + stock + ", author=" + author + ", members="
				+ members + "]";
	}
    
}
