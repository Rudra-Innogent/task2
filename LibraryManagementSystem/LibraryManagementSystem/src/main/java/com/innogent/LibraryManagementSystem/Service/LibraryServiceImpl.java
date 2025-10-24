package com.innogent.LibraryManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innogent.LibraryManagementSystem.Entity.*;
import com.innogent.LibraryManagementSystem.Repository.*;
import com.innogent.LibraryManagementSystem.DTO.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private MemberRepository memberRepo;


    @Override
    public MemberResponse borrowBook(Long memberId, Long bookId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() < 1) {
            throw new RuntimeException("Book out of stock");
        }

        book.setStock(book.getStock() - 1);
        member.getBorrowedBooks().add(book);

        bookRepo.save(book);
        memberRepo.save(member);

        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setBorrowedBookTitles(member.getBorrowedBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()));

        return response;
    }

    @Override
    public List<MemberResponse> getBorrowedBooksByMember(Long memberId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setBorrowedBookTitles(member.getBorrowedBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()));

        return Collections.singletonList(response);
    }
}
