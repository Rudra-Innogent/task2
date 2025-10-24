package com.innogent.LibraryManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innogent.LibraryManagementSystem.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}

