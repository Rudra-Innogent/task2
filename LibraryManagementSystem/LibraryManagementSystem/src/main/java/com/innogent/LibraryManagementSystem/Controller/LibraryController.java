package com.innogent.LibraryManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innogent.LibraryManagementSystem.Service.LibraryService;

import jakarta.transaction.Transactional;

import com.innogent.LibraryManagementSystem.DTO.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Transactional
    @PostMapping("/borrowBook/{memberId}/{bookId}")
    public MemberResponse borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        return libraryService.borrowBook(memberId, bookId);
    }

    @Transactional
    @GetMapping("/getBorrowedBooks/{memberId}")
    public List<MemberResponse> borrowedBooks(@PathVariable Long memberId) {
        return libraryService.getBorrowedBooksByMember(memberId);
    }
}
