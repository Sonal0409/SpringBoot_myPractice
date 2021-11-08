We have to create a following things step by Step
Libraryservice 1:
To add a book to library by passing JSON data 
{
"isbn":"5Nov",
"aisle":1,
"author":"Mittal1",
"book_name":"postmanv3"
}

We should get a response with a message and ID
{
    "msg": "Book added successfully",
    "id": "5Nov1"
}

Here id is combination of isbn and aisle

This data is to be added to a DB. So lets create a DB and table

************************************************************
Step 1 :
Download the mysql installer
Go to this link :
https://dev.mysql.com/downloads/installer/
Download button click on the second one. : Windows (x86, 32-bit), MSI Installer
Install the mysql install in your local machine.
Keep pressing next and install.
Set username : root
Password : root


Step2:
Go to search bar in your machine and enter mysql
Select mysql workbench option
You can also go to program files  mysql-mysqlWorkbechfolderMySQLWorkbench
Its an editior to perform any mysql operations

Open the editor.

Step3:

Create DataBase & table to add data for the service

Go to mysql  click on new sql tab
Run the following queries to create DB and table

CREATE DATABASE APIDevelopSpringBoot;
use APIDevelopSpringBoot;
CREATE TABLE Storage2(book_name varchar(50),id varchar(50),
isbn varchar(50),
aisle int,
author varchar(50),PRIMARY KEY (id));
INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Appium","fdsefr343","fdsefr3","43","Sonal Mittal");
select * from Storage2 where id = 'fdsefr343';

	Id is the primary key for the table,
	We have insterted a data in the table
	Select query to see the data

Step4:
Now let us give the DB properties in the SpringBoot project
Go to src/main/resources - application.properties file
Open it and add the DB properties like:
spring.datasource.url=jdbc:mysql://localhost:3306/APIDevelopSpringBoot
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto = update
spring.test.database.replace=none


Step 5 : 
Go to mvn repository and search for mysql-connector-java dependency and add it to POM

		<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
</dependency>

Step 6:
Uncomment the dependency in Pom file 
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

Now using the JPA methods you can do all the CRUD operation on your DB
The CRUD operations are : CREATE, UPDATE, DELETE

Step 7: 
Create Mapping entity Bean for the table in the DB, as to get the data and set the data in the table.
Just like we created a bean for greeting with getter and setter methods, using 2 variables id and content.
Similarly we are going to create a bean for the table also for all 5 columns
Bookname, id, isbn, aisle, author

Go to Controller package and create a class Library.java
Create following variables:

	private String book_name;
    
	private String id;
    
	private String isbn;
	private int aisle;
	private String author;
Select all the variables and right click on it   source generate getters and setters
Select all the variables Insertion point after author finish
Java bean is ready
For JPA to understand and ap it to your actual table we have to add more details
Add annotation @Entity over the class name  imprt form import javax.persistence.Entity;
@Entity
public class Library {
Add another annotation @Table(name=”Storage2”)  this will map the table to bean
@Entity
@Table(name="Storage2")
public class Library {
Add annotation @Column(name=”columnname”)   to map the table colums to the bean variables
@Column(name="book_name")
	private String book_name;
    @Column(name="id")
	private String id;
    @Column(name="isbn")
	private String isbn;
    @Column(name="aisle")
	private int aisle;
    @Column(name="author")
	private String author;


Add annotation @Id as it is primary key

@Column(name="book_name")
	private String book_name;
    @Id
    @Column(name="id")
	private String id;
    @Column(name="isbn")
	private String isbn;
    @Column(name="aisle")
	private int aisle;
    @Column(name="author")
	private String author;


Step 8: 
Add the JPA properties to application.properties file   I have already added
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto = update
spring.test.database.replace=none

Step 9:
Define Repository Interface by extending JPA repository interface
Create new Interface 
Go to com. com.Lesson4EndProject.com.Lesson4EndProject
Right click and create Interface
Give package name as com.Lesson4EndProject.com.Lesson4EndProject.repository
Interface Name: LibraryRepository
Under it  extended intefaces  click on ADD
Search for JpaRepository
Select the first one  click ADD
Click on finish

Now your interface will look like :

package com.mypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LibraryRepository extends JpaRepository<T, ID > {

}
This interface needs 2 parameters:
Provide the following:
T  Library   which is your bean class
ID  String  whichis the return type of your primary key of your table(this is needed to send queries suign primary key..eg methods like findByID etc etc)
Also import Library from com.mypractice.controller.Library

Now your class will be like
package com.mypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypractice.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String> {

}
Also you have to tell the library.java class that id is primary key

Add annotation @Id as it is primary key in class Library.java

@Column(name="book_name")
	private String book_name;
    @Id
    @Column(name="id")
	private String id;
    @Column(name="isbn")
	private String isbn;
    @Column(name="aisle")
	private int aisle;
    @Column(name="author")
	private String author;


Lets check now JPA methods are working or not. 
Check out setup is working or not

Go to the Springboot project  Runner file which is 
Application.java  main package  com.Lesson4EndProject.com.Lesson4EndProject

Only for this session I am overriding the run method. In some time we will do the crud operations from Postman only

Call the interface CommandLineRunner first :
@SpringBootApplication
public class Application implements CommandLineRunner {
}
Now add this method in the class:
@Override
	public void run(String args[]) {
}

Add the Libraryrepository interface so as to call JPA methods, use autowired
@Autowired
LibraryRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
Now go to the method and write inside the method:
@Override
public void run(String args[]) {
Library lib = repository.findById("generate2022").get();
System.out.println(lib.getAuthor());
// find the columns and return data for that id
// Here Library is your controller class or java bean.. where you have mentioned all the Strings
// the .get()  methods gets the values from DB and stores them in the lib object of type Library
// this will then map or set values to variables given in java bean.
		// now you can say and rerun this file or restart server
}  Now stop the server and run it again, it will get the data for you. You can right click and run the server too
*************************************************
Now write more JPA methods in the runner file to do more crud operations

// Now Add data to DB using set methods
// create an object for library

@Override
public void run(String args[]) {
Library lib = repository.findById("generate2022").get();
System.out.println(lib.getAuthor());
Library en =new Library();
		en.setAisle(123); 
		en.setAuthor("Marc");
	    en.setBook_name("Devops"); 
	    en.setIsbn("feq"); 
	    en.setId("feq123");
// save in the DB
	repository.save(en);
}
 Stop server and start server again
	Go to table and check if the data is available or not
If you want to extract all of the data from the tables and extract the bookname
	Before this lets comment out code for add the book.
	Comment out this line repository.save(en);
@Override
	public void run(String args[]) {
		
		Library lib=repository.findById("generate2022").get(); 
		System.out.println(lib.getAuthor());	
		// Now send data to DB using set methods
		// create an object for library
		Library en =new Library(); 
		en.setAisle(123); 
		en.setAuthor("Rahul");
	    en.setBook_name("Devops"); 
	    en.setIsbn("lkhs"); 
	    en.setId("lkhs123"); 
	    // repository.save(en); 
		// extract all the records from the DB, using loop and list
	     // start loop to fetch the required column value
	     // comment above creating code
	     List<Library> allrecords =repository.findAll();
		 for(Library item : allrecords)
		 { 
			 System.out.println(item.getBook_name());  
// you can get any column name
			 }
		}
Stop and start the server again



What ever we added , lets delete it also ::


@Override
	public void run(String args[]) {
		
		Library lib=repository.findById("generate2022").get(); 
		System.out.println(lib.getAuthor());	
		// Now send data to DB using set methods
		// create an object for library
		Library en =new Library(); 
		en.setAisle(123); 
		en.setAuthor("Rahul");
	    en.setBook_name("Devops"); 
	    en.setIsbn("lkhs"); 
	    en.setId("lkhs123"); 
	    // repository.save(en); 
		// extract all the records from the DB, using loop and list
	     // start loop to fetch the required column value
	     // comment above creating code
	     List<Library> allrecords =repository.findAll();
		 for(Library item : allrecords)
		 { 
			 System.out.println(item.getBook_name());  
// you can get any column name
// Delete the enttiy created
		 
		 repository.delete(en);
			 }
		}
Stop and start server.. the above code added will be deleted

Now let’s start developing out service calls

HTTP request is going to be POST
Endpoint is going to be http://localhost:8888/addBook

Body/Payload to be sent
{
"isbn":"5Nov",
"aisle":1,
"author":"Mittal1",
"book_name":"postmanv3"
}

Book should be added to table
Response should be with message and ID

{
    "msg": "Book added successfully",
    "id": "5Nov1"
}

If Book is already exist, we should get reposnse as
{
    "msg": "Book already exists”
}

Build status code as :
Status code : 201  success code
Status code  : 202  service accepted in case od book already exisits

Now start your server first
Go to postman and go to lesson 4 and create a new request

AddBook
Type is POST
Service as : http://localhost:8888/addBook
Body  raw JSON
{
"isbn":"5Nov",
"aisle":1,
"author":"Mittal1",
"book_name":"postmanv3"
}


Click on Send.. it will fail, explain that as it will search for rest controller tag in your springbook project
And serch for addBook service but that is not there so it will fail

Now lets build the service and test it


Go to Controller project and create a new class name as 
AddBookController or LibraryController and give annotation as @RestController

@RestController
public class AddBookController {
}

Now add the Service mapping and request name as /addbook
@PostMapping("/addBook")





Now create a void method first, we will change return type latter
Give @RequestBody => as we are passing a set of data and not single parameter
Also pass the bean class object , which is Library class and object is library
public void addBookImplementation(@RequestBody Library library)
	{ }
Now use the library object to map your passed values to the column data
Add this code
@Autowired
	LibraryRepository repository;
public void addBookImplementation(@RequestBody Library library)
	{
Step 2// But we have to write logic to set the id, which is combination of isbn and aisle number
		
		library.setId(library.getIsbn()+library.getAisle());
Step 1// add book details to DB , data given by user as test time
repository.save(library);  

// create object for Libraryrepository interface to call save method from JPA repository, autowired Libraryrepository

 }

Code will look like this now

package com.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mypractice.repository.LibraryRepository;

@RestController
public class AddBookController {
	
	@Autowired
	LibraryRepository repository;
	
	@PostMapping("/addBook")
	public void addBookImplementation(@RequestBody Library library)
	{
		library.setId(library.getIsbn()+library.getAisle());
             repository.save(library);	
	}


}
Go to the runner file
Comment the run method written by you and remove the implementing class too remove this  --  implements CommandLineRunner
Stop server and start again


package com.Lesson4EndProject.com.Lesson4EndProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


Go to postman and run the service
Request type should be post.

It will success with code as 200 
But there is no response yet

Go to DB and check you will have the new file and new id is also created


Now create a new Bean Class AddResponse.java
package com.mypractice.controller;

public class AddResponse {
	
	//Create 1 strings to print response message to user on add the book
	
   // create 1 string to print id to user when book is added succesfully
	
	// Select both the fields and create getters and setters.
	
	private String msg;
	private String id;
	
}

Now create setter and getters for these
Select all the string and right click  sourcegetters and setters   select variables after id  finish

Save the file and close

Go to your Add book controller class
@RestController
public class AddBookResponseMSGControler {
	@Autowired
	LibraryRepository repository;
	@PostMapping("/addBook")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
	{
	
		
		library.setId(library.getIsbn()+library.getAisle());
		repository.save(library);
// Create an Object for Addresponse bean to get the methods
		
		AddResponse ad = new AddResponse();
		ad.setMsg("Book added successfully");
		ad.setId(library.getIsbn()+library.getAisle());

		// Now you have to create the repsonse mesaage and id objects using java and java bean
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

Stop the server and start it again

Go to postman
In the request give new set of data now in the json body
And send the request
Request should be sent and response message should come

this is a Scenario to check if the book already exisits or not.
if it exists then give an error response.
	
In real time we always want to put the business logic like how to create id
To check if book exists or not in a seperate package and seperate class, seperate methods

and in the contorller class we call those methods.
	
	for this first create a new package service com.mypractice.service

	// in the package create a class LibraryService

package com.mypractice.service;

public class LibraryService {

}

Now create logic method to generate id for DB entry of book
Now add logic method to check if book is there or not.
	
so here lets create  2 methods 

  --> buildId(needs 2 parameters : isbn and aisle number)

//Step 2
@Service
public class LibraryService {
	
//step 1
	@Autowired
	LibraryRepository repository;
	
	public String buildId(String isbn, int aisle)
	{
		return isbn+aisle;
		
	}

Now go back to controller class and change the logic for buildid. Call the above method in the Libraryservice or ADDbook controller class






	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;

@PostMapping("/addBookAlreadyexists")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
	
{
	
		String id = libraryService.buildId(library.getIsbn(),library.getAisle());
		
		AddResponse ad = new AddResponse();


Now go to LibraryService.java class again

Build next method, its return type is going to be boolean

public boolean checkBookAlreadyexist(String id)
	{
		// Step1 : this method will take the id given by controller class and check 
		//if id exisits in db or not.
// lib is an object we are creating. Optional is a collection here
		
		Optional<Library> lib=repository.findById(id);
		
		if(lib.isPresent())
		
			return true;
		else
			return false;
		
		
		
		
	}
*****************************************
Go back to controller class now

Add following code for if condition

if(!libraryService.checkBookAlreadyexist(id)) // this methods return true or false
	    	 // if book doesnot exist..hence negation. then create the book as mentioned below.
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

	    	 // httpstatus accepted is 202, response is like request accepted 

	     }


This is a negative scenarios

Lets test is now

Start the server again



Go to poastman

Same request POSt

Change data  positive scenario
It will add book
Given positive response

__> now send the same request again..should give message it exisits

















After the service class is ready.. you call those methods in controller class..

















































































