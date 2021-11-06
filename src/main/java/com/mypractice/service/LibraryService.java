package com.mypractice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypractice.controller.Library;
import com.mypractice.repository.LibraryRepository;

// this annotation is needed so that springboot
//knows that in controller class it has to fetch methods from this service class

@Service
public class LibraryService {
	
	@Autowired
	LibraryRepository repository;
	
	public String buildId(String isbn, int aisle)
	{
		// Now add extra logic for unit testing
		// if user gives isbn starting with z then return old+isbn+aisle as ID
		// else return isbn+aisle as id
		if(isbn.startsWith("Z"))
		{
			return "OLD"+isbn+aisle;
		}
			// this is before unit testing
		return isbn+aisle;
		
	}
	
	public boolean checkBookAlreadyexist(String id)
	{
		// Step1 : this method will take the id given by controller class and check 
		//if id exisits in db or not.
		// go backt o controller now
		Optional<Library> lib=repository.findById(id);
		
		if(lib.isPresent())
		
			return true;
		else
			return false;
		
		
		
		
	}

}
