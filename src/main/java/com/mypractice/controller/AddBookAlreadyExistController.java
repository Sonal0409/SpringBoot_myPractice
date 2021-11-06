package com.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.repository.LibraryRepository;
import com.mypractice.service.LibraryService;

@RestController
public class AddBookAlreadyExistController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;

// this is a Scenario to check if the book already exisits or not.
//	if it exists then give an error response.
	
	// In real time we always want to put the business logic like how to create id
	// to check if book exists or not in a seperate package and seperate class, seperate methods
	// and in the contorller class we call those methods.
	
	// for this first create a new package service com.mypractice.service
	// in the package create a class LibraryService
	// create logic method to generate id for DB entry of book
	// add logic method to check if book is there or not.
	
	// After the service class is ready.. you call those methods in controller class..
	// so created 2 methods --> buildId(needs 2 parameters : isbn and aisle number)
	// ---> checkBookAlreadyexisits(needs 1 parameter Book id);
	
	
	
	@PostMapping("/addBookAlreadyexists")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
	{
	
		String id = libraryService.buildId(library.getIsbn(),library.getAisle());
		
		AddResponse ad = new AddResponse();
	     if(!libraryService.checkBookAlreadyexist(id)) // this methods return true or false
	    	 // if book doesnot exist..hence neagtion. then create the book as mentioned below.
	     {	
	    library.setId(id);
		repository.save(library);
		
		// Create an Object for Addresponse bean to get the methods
		
		
		ad.setMsg("Book added successfully");
		ad.setId(id);
        return new ResponseEntity<AddResponse>(ad,HttpStatus.CREATED);
		
	}
	     else
	     {
	    	 ad.setMsg("Book already Exists");
	    	 ad.setId(id);
	     
	    	 return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED); 
	    	 // httpstatus accepted is 202, error response
	     }

	}


}
