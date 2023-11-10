package com.example.controller;

import com.example.DTO.BookDTO;
import com.example.entity.Book;
import com.example.service.BookService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Controller", description = "Book API")
public class BookController {

    Gson gson = new Gson();

    @Autowired
    private BookService bookService;

    @Operation(summary = "Get all books", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all books",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Book.class))
                            )
                    }
            )
    })
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Get book by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found book",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class)
                            )
                    }
            )
    })
    @Parameter(name = "id", description = "Book ID for searching")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id)  {
        Book book = bookService.getBookById(id);
        return book;
    }

    @Operation(summary = "Add book", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book was added"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to add", required = true, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))
    })
    @PostMapping("/add")
    public HttpStatus addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return HttpStatus.CREATED;
    }

    @Operation(summary = "Delete book by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book was deleted"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Book ID for delete")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return HttpStatus.OK;
    }

    @Parameter(in = ParameterIn.PATH, name = "id", description = "Book ID to change")
    @Operation(summary = "Change book", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book was changed"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to change", required = true, content = {
            @Content(schema = @Schema(implementation = Book.class))
    })
    @PatchMapping("/change/{id}")
    public HttpStatus changeBook(@PathVariable int id, @RequestBody Book book) {
        bookService.changeBook(id, book);
        return HttpStatus.OK;
    }

    @GetMapping("/getBooks")
    public List<Book> getAllBooksForPage() {
        return bookService.getAllBooks();
    }

}
