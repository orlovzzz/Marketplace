package com.example.service;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookS")
@Getter
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book) {
        if (authorService.getAuthorById(book.getAuthor().getId()) != null) {
            book.setAuthor(authorService.getAuthorById(book.getAuthor().getId()));
            bookRepository.save(book);
        }
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public void changeBook(int id, Book book) {
        JsonObject json = new JsonObject();
        Book book_ch = getBookById(id);
        if (book_ch != null) {
            if (book.getName() != null) book_ch.setName(book.getName());
            if (book.getNumberSeller() != null) book_ch.setNumber(book.getNumber());
            if (book.getPrice() != 0) book_ch.setPrice(book.getPrice());
            if (book.getAuthor() != null && authorService.getAuthorById(book.getAuthor().getId()) != null) {
                book_ch.setAuthor(authorService.getAuthorById(book.getAuthor().getId()));
            }
            if (book.getNumber() != 0) book_ch.setNumber(book.getNumber());
            bookRepository.save(book_ch);
        }
    }

    public Book getBookByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        if(!books.isEmpty()) {
            return books.get(0);
        } else {
            return null;
        }
    }

}
