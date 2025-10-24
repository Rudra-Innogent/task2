package com.innogent.LibraryManagementSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innogent.LibraryManagementSystem.DTO.AuthorResponse;
import com.innogent.LibraryManagementSystem.DTO.BookRequest;
import com.innogent.LibraryManagementSystem.DTO.BookResponse;
import com.innogent.LibraryManagementSystem.Entity.Author;
import com.innogent.LibraryManagementSystem.Entity.Book;
import com.innogent.LibraryManagementSystem.Repository.AuthorRepository;
import com.innogent.LibraryManagementSystem.Repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private AuthorRepository authorRepo;
    
	 @Override
	    public BookResponse addBook(BookRequest request) {
		        Author author = authorRepo.findById(request.getAuthorId())
		                .orElseThrow(() -> new RuntimeException("Author not found"));

		        Book book = new Book();
		        book.setTitle(request.getTitle());
		        book.setStock(request.getStock());
		        book.setAuthor(author);
		        bookRepo.save(book);

		        
		        List<Book> authorBooks = author.getBooks();
		        authorBooks.add(book);
		        author.setBooks(authorBooks);
		        authorRepo.save(author);

		        BookResponse response = new BookResponse();
		        response.setId(book.getId());
		        response.setTitle(book.getTitle());
		        response.setStock(book.getStock());
		        response.setAuthorName(author.getName());
		        return response;	    }

	@Override
	public BookResponse findById(long id) {
		Book book=bookRepo.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Book not found with ID: " + id));
		BookResponse response=new BookResponse();
		response.setAuthorName(book.getAuthor().getName());
		response.setId(book.getId());
		response.setStock(book.getStock());
		response.setTitle(book.getTitle());
		return response;
	}

	@Override
	public void deleteBookById(long id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public BookResponse update(BookRequest request, long id) {
        Book book = bookRepo.findById(id).orElseThrow(
        		() -> new RuntimeException("Author not found"));
        book.setTitle(request.getTitle());
        book.setStock(request.getStock());
        bookRepo.save(book);
        
		BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setStock(book.getStock());
        response.setAuthorName(book.getAuthor().getName());
		return response;
	}

	@Override
	public List<BookResponse> findAllBooks() {
		List<Book> book=bookRepo.findAll();
		List<BookResponse> response=new ArrayList<BookResponse>();
		for(Book newBook:book) {
			BookResponse bookResponse = new BookResponse();
			bookResponse.setId(newBook.getId());
	        bookResponse.setTitle(newBook.getTitle());
	        bookResponse.setStock(newBook.getStock());
	        bookResponse.setAuthorName(newBook.getAuthor().getName());
			response.add(bookResponse);
		}
		return response;
	}

}
