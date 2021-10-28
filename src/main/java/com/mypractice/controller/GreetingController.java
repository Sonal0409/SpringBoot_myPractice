package com.mypractice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	// we want to create a Rest Call as http://localhost:8080/greeting?name=Simplilearn
	// here http://localhost:8080 : ipaddress , /greeting : resource , ?name=Simplilearn --> this is query parameter
	// 1. call is get call
	// it means that when user gives GET call in postman and after ipaddresss gives resource as /greeting..then call this method
	
	
	// here we are doing dependency inject from springboot so as to automatically create an object for your class using @autowired annotation
	// now you dont have to give classname obj = new classname();
	@Autowired
	Greeting greeting;
	
	AtomicLong counter = new AtomicLong();
	
	@GetMapping("/greeting")
	// Step 1: 
	public Greeting greeting(@RequestParam(value="name")String name)
	
	// here @RequestParam (value="name") --> means that we will pass parameter with the request , (value="name") --> capture the value of parameter
	// parameter is name and we are also declaring String name which means its variable to be used inside the method
	{
		//logic to give back the response back to GET call
		
		// in the output we need 2 variables id & message 
		// for this we have to create Java Bean 
		//once java bean is ready use the object of bean class to set values
		
		// Now set the value for id..but this value should increment automatically.
		// use java class called AtomicLong  from java, create an object of it above and use it
		
		greeting.setId(counter.incrementAndGet());
		
		greeting.setContent("hey i am learning Springboot from " + name); // using setContent method we have set the content
		
		return greeting;
		

		
		
		
		
	}
	
	

}
