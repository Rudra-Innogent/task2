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
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public AuthorResponse addAuthor(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBooks(new ArrayList<>()); // initialize empty list
        authorRepo.save(author);

        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBookTitles(new ArrayList<>());
        return response;
    }

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
        return response;
    }

    @Override
    public MemberResponse addMember(MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setBorrowedBooks(new HashSet<>()); // initialize borrowed books
        memberRepo.save(member);

        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setBorrowedBookTitles(new ArrayList<>());
        return response;
    }

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
