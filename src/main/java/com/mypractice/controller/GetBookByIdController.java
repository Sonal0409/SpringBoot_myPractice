package com.mypractice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mypractice.repository.LibraryRepository;

@RestController
public class GetBookByIdController {
	
	@Autowired
	LibraryRepository repository;
	// here we are giving request as getBooks and a path parameter as id.
	// here id is not query parameter
	// here id is the variable that user passes from postman.
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value="id")String id)
	{
		// Step 1 ----->write the logic to get the book details from DB by querying the id
		
		// Step 2: Now write logic if id doesnot exist, we should get repsonse message book doesnot exist
		// for this place the code in try and catch block first
		// and then fill up exception code in catch block.
		try
		{
	Library lib =	repository.findById(id).get();
	return lib;
		// here automatically the response will be converted to json format
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); //returns error code 404
		}
		
		// now test it with valid id and invalid id
	}
	
	
	
	

}
