package com.innogent.LibraryManagementSystem.DTO;

import org.springframework.stereotype.Component;

@Component
public class AuthorRequest {

	    private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	    
	

}
