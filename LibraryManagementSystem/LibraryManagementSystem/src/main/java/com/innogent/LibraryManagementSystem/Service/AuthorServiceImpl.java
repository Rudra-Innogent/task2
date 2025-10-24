package com.innogent.LibraryManagementSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innogent.LibraryManagementSystem.DTO.AuthorRequest;
import com.innogent.LibraryManagementSystem.DTO.AuthorResponse;
import com.innogent.LibraryManagementSystem.Entity.Author;
import com.innogent.LibraryManagementSystem.Entity.Book;
import com.innogent.LibraryManagementSystem.Repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
    private AuthorRepository authorRepo;
	
	 @Override
	    public AuthorResponse addAuthor(AuthorRequest request) {
	        Author author = new Author();
	        author.setName(request.getName());
	        author.setBooks(new ArrayList<>());
	        authorRepo.save(author);

	        AuthorResponse response = new AuthorResponse();
	        response.setId(author.getId());
	        response.setName(author.getName());
	        response.setBookTitles(new ArrayList<>());
	        return response;
	    }

	@Override
	public AuthorResponse findById(long id) {
		Author author=authorRepo.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Author not found with ID: " + id));
		
		AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBookTitles(author.getBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()));
        return response;
	}

	@Override
	public void deleteById(long id) {
		authorRepo.deleteById(id);
		
	}

	@Override
	public AuthorResponse update(AuthorRequest request, long id) {
		Author author=authorRepo.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Author not found with ID: " + id));
		author.setName(request.getName());
		authorRepo.save(author);
		AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBookTitles(author.getBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()));
        return response;
	}

	@Override
	public List<AuthorResponse> findAllAuthors() {
		List<Author> list=authorRepo.findAll();
		List<AuthorResponse> response=new ArrayList<AuthorResponse>();
		for(Author author:list) {
			AuthorResponse authorResponse = new AuthorResponse();
	        authorResponse.setId(author.getId());
	        authorResponse.setName(author.getName());
	        authorResponse.setBookTitles(author.getBooks()
	                .stream()
	                .map(Book::getTitle)
	                .collect(Collectors.toList()));
			response.add(authorResponse);
		}
		return response;
	}
}
