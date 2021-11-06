package com.mypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypractice.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>,LibraryRepositoryCustom {

}
