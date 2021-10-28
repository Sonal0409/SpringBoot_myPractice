package com.mypractice.controller;

import org.springframework.stereotype.Component;

// @compoonent indicates sprintboot that current class is a component/bean, inject it in the controller class

@Component
public class Greeting {
	
	private long id;
	private String content;
	
	//generate getters and setters for these variables
	// right click --> go to source -->generate getters and setters --->select the variables --> select insertion point to be After 'Content'
	// this is java bean
	
	// here setid and setcontent is used to set values to 2 variables
	// you can fetch the values using getter methods
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	


	
	

}
