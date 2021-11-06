package com.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.repository.LibraryRepository;
@RestController
public class DeleteBookController {
	
	@Autowired
	LibraryRepository repository;
	// here we are going to give input as id and that book details
	// should be deleted from the DB
	// user shoudl also get a response message that Book is deleted.
	
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library)
	{
		Library del =repository.findById(library.getId()).get();
		repository.delete(del);
		
		return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);
	}

}
