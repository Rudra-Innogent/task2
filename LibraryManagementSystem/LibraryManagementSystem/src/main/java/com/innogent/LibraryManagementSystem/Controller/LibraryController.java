package com.innogent.LibraryManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innogent.LibraryManagementSystem.Service.LibraryService;
import com.innogent.LibraryManagementSystem.DTO.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // Add Author
    @PostMapping("/addAuthors")
    public AuthorResponse addAuthor(@RequestBody AuthorRequest request) {
        return libraryService.addAuthor(request);
    }

    // Add Book
    @PostMapping("/addBooks")
    public BookResponse addBook(@RequestBody BookRequest request) {
        return libraryService.addBook(request);
    }

    // Add Member
    @PostMapping("/addMember")
    public MemberResponse addMember(@RequestBody MemberRequest request) {
        return libraryService.addMember(request);
    }

    // Borrow Book
    @PostMapping("/borrowBook/{memberId}/{bookId}")
    public MemberResponse borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        return libraryService.borrowBook(memberId, bookId);
    }

    // Get Borrowed Books for a Member
    @GetMapping("/getBorrowedBooks/{memberId}")
    public List<MemberResponse> borrowedBooks(@PathVariable Long memberId) {
        return libraryService.getBorrowedBooksByMember(memberId);
    }
}
