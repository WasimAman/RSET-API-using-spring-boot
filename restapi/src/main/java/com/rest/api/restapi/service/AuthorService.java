package com.rest.api.restapi.service;

import com.rest.api.restapi.DTOs.Authors;
import com.rest.api.restapi.DTOs.BookDetails;
import com.rest.api.restapi.entities.Author;
import com.rest.api.restapi.entities.Book;
import com.rest.api.restapi.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public int saveAuthor(Author author) {
        Author authorIn = new Author(author.getName(), author.getCountry());
        List<Book> listOfBooks = new ArrayList<>();

        for (Book bookIn : author.getBooks()) {
            Book book = new Book(bookIn.getName(), bookIn.getPrice());
            book.setAuthor(authorIn);
            listOfBooks.add(book);
        }

        authorIn.setBooks(listOfBooks);
        return authorRepository.save(authorIn).getId();
    }

    public Authors getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return getAuthors(author);
    }

    public List<Authors> getAllAuthors() {
        List<Author> listOfAuthors = authorRepository.findAll();
        List<Authors> authors = new ArrayList<>();
        for (Author authorIn : listOfAuthors) {
            authors.add(getAuthors(authorIn));
        }
        return authors;
    }

    public int updateAuthor(int id, Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author authorIn = optionalAuthor.get();
            authorIn.setName(author.getName());
            authorIn.setCountry(author.getCountry());
            return authorRepository.save(authorIn).getId();
        } else {
            throw new EntityNotFoundException("Author not found");
        }
    }

    public void deleteAuthorById(int authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) {
            throw new EntityNotFoundException("Author not found");
        }

        Author author = optionalAuthor.get();
        authorRepository.delete(author);
    }

    private Authors getAuthors(Author author) {
        Authors authors = new Authors(author.getName(), author.getCountry());
        List<BookDetails> books = new ArrayList<>();
        for (Book book : author.getBooks()) {
            books.add(new BookDetails(book.getId(), book.getName(), book.getPrice()));
        }
        authors.setBooks(books);
        return authors;
    }
}
