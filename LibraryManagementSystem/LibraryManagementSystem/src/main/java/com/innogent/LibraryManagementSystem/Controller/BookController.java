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
import com.innogent.LibraryManagementSystem.DTO.BookRequest;
import com.innogent.LibraryManagementSystem.DTO.BookResponse;
import com.innogent.LibraryManagementSystem.Service.BookService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	@Transactional
    @PostMapping("/add")
    public BookResponse addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }
	
	@Transactional
    @GetMapping("/find/{id}")
    public BookResponse findBook(@PathVariable long id) {
        return bookService.findById(id);
    }
	
	@Transactional
    @PutMapping("/update/{id}")
    public BookResponse updateBook(@RequestBody BookRequest request ,@PathVariable long id) {
        return bookService.update(request,id);
    }
	
	@Transactional
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
         bookService.deleteBookById(id);
    }
	
	@Transactional
    @GetMapping("/getAll")
    public List<BookResponse> findAll() {
        return bookService.findAllBooks();
    }
	
}
