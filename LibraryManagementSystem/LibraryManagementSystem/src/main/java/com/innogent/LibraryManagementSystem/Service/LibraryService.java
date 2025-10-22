package com.innogent.LibraryManagementSystem.Service;

import com.innogent.LibraryManagementSystem.DTO.*;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LibraryService {
		
		@Transactional
	 	AuthorResponse addAuthor(AuthorRequest request);
	 	
	 	@Transactional
	    BookResponse addBook(BookRequest request);

	 	@Transactional
	 	MemberResponse addMember(MemberRequest request);

	 	@Transactional
	 	MemberResponse borrowBook(Long memberId, Long bookId);

	 	@Transactional
	 	List<MemberResponse> getBorrowedBooksByMember(Long memberId);
}

