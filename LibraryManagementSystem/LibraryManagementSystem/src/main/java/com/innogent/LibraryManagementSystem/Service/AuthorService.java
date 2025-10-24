package com.innogent.LibraryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.innogent.LibraryManagementSystem.DTO.AuthorRequest;
import com.innogent.LibraryManagementSystem.DTO.AuthorResponse;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface AuthorService {
	@Transactional(rollbackFor = Exception.class)
	AuthorResponse addAuthor(AuthorRequest request);
	
	AuthorResponse findById(long id);
	
	void deleteById(long id);
	
	AuthorResponse update(AuthorRequest request, long id);
	
	List<AuthorResponse> findAllAuthors();
	
}
