package com.rest.api.restapi.service;

import com.rest.api.restapi.DTOs.AuthorDetails;
import com.rest.api.restapi.DTOs.Books;
import com.rest.api.restapi.entities.Author;
import com.rest.api.restapi.entities.Book;
import com.rest.api.restapi.repository.AuthorRepository;
import com.rest.api.restapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public int saveBook(Book book) {
        Book bookIn = new Book(book.getName(), book.getPrice());

        Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getId());
        Author author;

        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
            bookIn.setAuthor(author);
            author.getBooks().add(bookIn);
        } else {
            author = new Author(book.getAuthor().getName(), book.getAuthor().getCountry());
            bookIn.setAuthor(author);
            List<Book> listOfBooks = new ArrayList<>();
            listOfBooks.add(bookIn);
            author.setBooks(listOfBooks);
        }

        return authorRepository.save(author).getId();
    }

    public List<Books> getAllBooks() {
        List<Book> listOfBooks = bookRepository.findAll();
        List<Books> books = new ArrayList<>();
        for (Book book : listOfBooks) {
            books.add(getBook(book));
        }
        return books;
    }

    public Books getBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return getBook(book);
    }

    public int updateBook(int id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Optional<Author> optionalAuthor = authorRepository.findById(bookDetails.getAuthor().getId());
        Author author;
        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
        } else {
            author = new Author(bookDetails.getAuthor().getName(), bookDetails.getAuthor().getCountry());
            authorRepository.save(author);
        }

        book.setName(bookDetails.getName());
        book.setPrice(bookDetails.getPrice());
        book.setAuthor(author);

        return bookRepository.save(book).getId();
    }

    public void deleteBookById(int bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new EntityNotFoundException("Book not found");
        }

        Book book = optionalBook.get();
        bookRepository.delete(book);
    }

    private Books getBook(Book book) {
        Author author = book.getAuthor();
        AuthorDetails authorDetails = new AuthorDetails(author.getId(), author.getName(), author.getCountry());
        return new Books(book.getName(), book.getPrice(), authorDetails);
    }
}
