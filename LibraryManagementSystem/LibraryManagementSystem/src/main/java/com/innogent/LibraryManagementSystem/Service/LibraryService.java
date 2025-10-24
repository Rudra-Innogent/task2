package com.innogent.LibraryManagementSystem.Service;

import com.innogent.LibraryManagementSystem.DTO.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface LibraryService {

	    @Transactional(rollbackFor = Exception.class)
	 	MemberResponse borrowBook(Long memberId, Long bookId);

	    @Transactional(rollbackFor = Exception.class)
	 	List<MemberResponse> getBorrowedBooksByMember(Long memberId);
}

   