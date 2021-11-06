package com.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.repository.LibraryRepository;

@RestController
public class AddBookResponseMSGControler {
	@Autowired
	LibraryRepository repository;
	
	
	//We are creating a request for Library API for scenrio 1
	// Post book details in the DB
	// so here type of request is POST, resource mapping is addBook
	// In this scenario we are sendinga Json body parameter, thats is why @RequestBody
	// Then give the bean class Library and a objectname library
	
	@PostMapping("/addBookWithResponse")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
	{
	
		
		library.setId(library.getIsbn()+library.getAisle());
		repository.save(library);
		
		// Create an Object for Addresponse bean to get the methods
		
		AddResponse ad = new AddResponse();
		ad.setMsg("Book added successfully");
		ad.setId(library.getIsbn()+library.getAisle());
		// Now you have to created the repsonse mesaage and id objects using java and java bean
		// but you have to send it to the user as JSON object
		// for this use ResponseEntity object
		// it will automatically convert the java mapping(Bean methods) to JSON and show to user
		// ad here is response message and book id
		// https status is repsonse code which is 201
		
		return new ResponseEntity<AddResponse>(ad,HttpStatus.CREATED);
		
		// ResponseEntity is a class in springboot that holds all your response 
		//and will display the desired response to user.
		// start server and execute the code.
		
		// output should look like 
		// "msg": "Book added successfully",
	    //  "id": "nov20"
		
		
		
		
		
	}


}
