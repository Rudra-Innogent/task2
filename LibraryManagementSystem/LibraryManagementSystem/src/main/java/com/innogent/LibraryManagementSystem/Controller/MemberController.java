package com.innogent.LibraryManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.innogent.LibraryManagementSystem.DTO.MemberRequest;
import com.innogent.LibraryManagementSystem.DTO.MemberResponse;
import com.innogent.LibraryManagementSystem.Service.MemberService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	    @Transactional
	    @PostMapping("/add")
	    public MemberResponse addMember(@RequestBody MemberRequest request) {
	        return memberService.addMember(request);
	    }
	  
	    @Transactional
	    @GetMapping("/find/{id}")
	    public MemberResponse findBook(@PathVariable long id) {
	        return memberService.findById(id);
	    }
		
		@Transactional
	    @PutMapping("/update/{id}")
	    public MemberResponse updateBook(@RequestBody MemberRequest request ,@PathVariable long id) {
	        return memberService.update(request,id);
	    }
		
		@Transactional
	    @DeleteMapping("/delete/{id}")
	    public void delete(@PathVariable long id) {
	         memberService.deleteById(id);
	    }
		
		@Transactional
	    @GetMapping("/getAll")
	    public List<MemberResponse> findAll() {
	        return memberService.findAllMembers();
	    }
}
