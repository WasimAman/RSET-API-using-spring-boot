package com.rest.api.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.api.restapi.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
