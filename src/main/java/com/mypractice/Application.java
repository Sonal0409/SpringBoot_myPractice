package com.mypractice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mypractice.controller.Library;
import com.mypractice.repository.LibraryRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	LibraryRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String args[]) {
		
		Library lib=repository.findById("generate2022").get(); 
		// find the columns and return data for that id
		// Here Library is your controller class or java bean.. where you have mentioned all the Strings
		// the .get()  methods gets the values from DB and stores them in the lib object of type Library
		// this wil then map or set values to variables given in java bean.
		// now you can say and rerun this file or restart server
		System.out.println(lib.getAuthor());
		
		// Now send data to DB using set methods
		// create an object for library
		
		Library en =new Library(); 
		
		en.setAisle(123); 
		en.setAuthor("Rahul");
	    en.setBook_name("Devops"); 
	    en.setIsbn("lkhs"); 
	    en.setId("lkhs123"); 
	 // save in the DB
	 
	    // repository.save(en); 
		
		// extract all the records from the DB, using loop and list
	     // start loop to fetch the required column value
	     // comment above creating code
	     List<Library> allrecords =repository.findAll();
		 
		 for(Library item : allrecords)
		 { 
			 System.out.println(item.getBook_name()); 
			 }
		 
		 // Delete the enttiy created
		 
		 repository.delete(en);
		
		
	}

}
