package com.mypractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.repository.LibraryRepository;


@RestController
public class GetBookByAuthorController {
	
	@Autowired
	LibraryRepository repository;
	
	// here we have query parameter
	// here we have to create a customized method out of JPA

	@GetMapping("getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
	{
		return repository.findAllByAuthor(authorname);
	}
}
