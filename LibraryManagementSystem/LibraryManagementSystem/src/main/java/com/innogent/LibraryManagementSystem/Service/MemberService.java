package com.innogent.LibraryManagementSystem.Service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.innogent.LibraryManagementSystem.DTO.*;

@Service
public interface MemberService {

	@Transactional(rollbackFor = Exception.class)
	MemberResponse addMember(MemberRequest request);
	
	@Transactional(rollbackFor = Exception.class)
	MemberResponse findById(long id);
	
	@Transactional(rollbackFor = Exception.class)
	void deleteById(long id);
	
	@Transactional(rollbackFor = Exception.class)
	MemberResponse update(MemberRequest request, long id);
	
	@Transactional(rollbackFor = Exception.class)
	List<MemberResponse> findAllMembers();
}
