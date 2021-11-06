package com.mypractice;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mypractice.controller.AddBookAlreadyExistController;

import com.mypractice.controller.Library;

import com.mypractice.repository.LibraryRepository;
import com.mypractice.service.LibraryService;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	AddBookAlreadyExistController con;
	
	// for Junit testing
	
		@MockBean
		LibraryRepository repository;
		
		// for Junit testing
		
		@MockBean
		LibraryService libraryService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void checkBuildIDlogic()
	{
		// Create an object of LibraryService class so as to test the Build ID 1st method logic
		LibraryService lib = new LibraryService();
		String id = lib.buildId("Zman", 24);
		// this method should return output as OLDzman24
		System.out.println(id);
		Assertions.assertEquals("OLDZman24", id);
		
	}
	
	@Test
	public void checkBuildIDlogicAgain()
	{
		// Create an object of LibraryService class so as to test the Build ID 1st method logic
		LibraryService lib = new LibraryService();
		String id = lib.buildId("man", 24);
		// this method should return output as OLDzman24
		System.out.println(id);
		Assertions.assertEquals("man24", id);
		
	}
	
	
	// Write Unit test case for Addbook method
	
	
	@Test
	public void AddBookControllerTest()
	{
		//mockito frameowrk
		Library lib = buildLibrary();

		when(libraryService.buildId(lib.getIsbn(),lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyexist(lib.getId())).thenReturn(false);
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		Assertions.assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	}
	
	@Test
	public void AddBookControllerTestAgain()
	{
		//mockito frameowrk
		Library lib = buildLibrary();

		when(libraryService.buildId(lib.getIsbn(),lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyexist(lib.getId())).thenReturn(true);
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		Assertions.assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
	}
	
	// the above addBookImplementation() method needs a library object that takes input for all the fields.
	// in unit testing we have to pass all the vlaues as a java object
	//through postman you passed the values as Json body and it was converted to java object by spring boot
	//with unit testing you are not connected to server..just checking the logic..
	//so you have to create the data and pass to the method and test it
	
	// creation of data for library object body

	public Library buildLibrary()
	{
		Library lib =new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("sfe");
		lib.setAuthor("Rahul Shetty");
		lib.setId("sfe3b");
		return lib;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
