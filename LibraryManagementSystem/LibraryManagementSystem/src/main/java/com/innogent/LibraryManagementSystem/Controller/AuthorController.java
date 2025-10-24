package com.innogent.LibraryManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innogent.LibraryManagementSystem.DTO.AuthorRequest;
import com.innogent.LibraryManagementSystem.DTO.AuthorResponse;
import com.innogent.LibraryManagementSystem.Service.AuthorService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@Transactional
    @PostMapping("/add") 
    public AuthorResponse addAuthor(@RequestBody AuthorRequest request) {
        return authorService.addAuthor(request);
    }
	
	@Transactional
    @GetMapping("/find/{id}") 
    public AuthorResponse findAuthorById(@PathVariable long id) {
        return authorService.findById(id);
    }
	
	@Transactional
    @PutMapping("/update/{id}") 
    public AuthorResponse updateAuthorById(@RequestBody AuthorRequest request,@PathVariable long id) {
        return authorService.update(request,id);
    }
	
	@Transactional
    @DeleteMapping("/delete/{id}") 
    public void deleteAuthorById(@PathVariable long id) {
         authorService.deleteById(id);
    }
	
	@Transactional
    @GetMapping("/findAll") 
    public List<AuthorResponse> getAllAuthors() {
        return authorService.findAllAuthors();
    }
	
	
}
