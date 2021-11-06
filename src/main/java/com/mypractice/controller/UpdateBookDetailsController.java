package com.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.repository.LibraryRepository;

@RestController
public class UpdateBookDetailsController {
	
	@Autowired
	LibraryRepository repository;
	
	// in this controller, we are going to query a book id
	// and update its details based on the payload that is sent as input in the DB table
	// On success show Json repsonse to user
	
	// here input to the req will be id and payload(updates to book)
	
	
	@PutMapping("/updateBook/{id}") // here is is a path variable
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library)
	{
	
		Library exisitingBook = repository.findById(id).get();
		
		exisitingBook.setAisle(library.getAisle());
		
		exisitingBook.setAuthor(library.getAuthor());
		
		exisitingBook.setBook_name(library.getBook_name());
		repository.save(exisitingBook);
		
		
		// Also send the respose to user upon updting the details
		
		return new ResponseEntity<Library>(exisitingBook, HttpStatus.OK);
		
	}

}
