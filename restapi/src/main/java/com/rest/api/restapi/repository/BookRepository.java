package com.rest.api.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.api.restapi.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
