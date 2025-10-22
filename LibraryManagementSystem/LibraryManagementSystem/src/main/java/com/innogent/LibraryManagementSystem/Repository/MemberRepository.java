package com.innogent.LibraryManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innogent.LibraryManagementSystem.Entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { 
	
}
