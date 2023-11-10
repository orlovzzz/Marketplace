package com.example.service;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    public void changeAuthor(int id, Author author) {
        Author author_c = authorRepository.findById(id).orElse(null);
        if (author_c != null) {
            if (author.getName() != null) author_c.setName(author.getName());
            if (author.getSurname() != null) author_c.setSurname(author.getSurname());
            authorRepository.save(author_c);
        }
    }

}
