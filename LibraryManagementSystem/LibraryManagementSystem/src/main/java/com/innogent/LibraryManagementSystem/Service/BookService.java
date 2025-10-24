package com.innogent.LibraryManagementSystem.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.innogent.LibraryManagementSystem.DTO.AuthorRequest;
import com.innogent.LibraryManagementSystem.DTO.BookResponse;
import com.innogent.LibraryManagementSystem.DTO.BookRequest;

@Service
public interface BookService {
	@Transactional(rollbackFor = Exception.class)
	BookResponse addBook(BookRequest request);
	
	@Transactional(rollbackFor = Exception.class)
	BookResponse findById(long id);
	
	@Transactional(rollbackFor = Exception.class)
	void deleteBookById(long id);
	
	@Transactional(rollbackFor = Exception.class)
	BookResponse update(BookRequest request, long id);
	
	@Transactional(rollbackFor = Exception.class)
	List<BookResponse> findAllBooks();
}
