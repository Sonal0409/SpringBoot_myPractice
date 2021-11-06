package com.mypractice.repository;

import java.util.List;

import com.mypractice.controller.Library;

public interface LibraryRepositoryCustom {
	

	List<Library> findAllByAuthor(String authorName);

}
