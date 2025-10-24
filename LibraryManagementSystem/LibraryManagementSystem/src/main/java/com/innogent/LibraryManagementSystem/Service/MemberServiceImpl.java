package com.innogent.LibraryManagementSystem.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innogent.LibraryManagementSystem.DTO.MemberRequest;
import com.innogent.LibraryManagementSystem.DTO.MemberResponse;
import com.innogent.LibraryManagementSystem.Entity.Book;
import com.innogent.LibraryManagementSystem.Entity.Member;
import com.innogent.LibraryManagementSystem.Repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
    private MemberRepository memberRepo;
	
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
	public MemberResponse findById(long id) {
		 Member member = memberRepo.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

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
	public void deleteById(long id) {
		memberRepo.deleteById(id);
		
	}

	@Override
	public MemberResponse update(MemberRequest request, long id) {
		Member member = memberRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
		 	member.setName(request.getName());
	        member.setEmail(request.getEmail());
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
	public List<MemberResponse> findAllMembers() {
	List<Member> members=memberRepo.findAll();
	List<MemberResponse> response=new ArrayList<MemberResponse>();
	for(Member member:members) {
		MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setName(member.getName());
        memberResponse.setEmail(member.getEmail());
        memberResponse.setBorrowedBookTitles(member.getBorrowedBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()));
        response.add(memberResponse);
		
	}
		return response;
	}
}
