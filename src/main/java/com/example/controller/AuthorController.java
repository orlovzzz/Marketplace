package com.example.controller;

import com.example.entity.Author;
import com.example.service.AuthorService;
import com.google.gson.Gson;
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

@Tag(name = "Author Controller", description = "Author API")
@RestController
@RequestMapping("/author")
public class AuthorController {

    private Gson gson = new Gson();
    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Get all authors", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all authors",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Author.class))
                            )
                    }
            )
    })
    @GetMapping("")
    public String getAllAuthors() {
        return gson.toJson(authorService.getAllAuthors());
    }

    @Operation(summary = "Get author by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found author",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Author.class)
                            )
                    }
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Author ID")
    @GetMapping("/{id}")
    public String getAuthorById (@PathVariable int id) {
        return gson.toJson(authorService.getAuthorById(id));
    }

    @Operation(summary = "Add author", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Author was added"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Author to add", required = true, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))
    })
    @PostMapping("/add")
    public HttpStatus addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return HttpStatus.CREATED;
    }

    @Operation(summary = "Delete author by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Author was deleted")
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Author ID to delete")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return HttpStatus.OK;
    }

    @Operation(summary = "Change author", responses = {
            @ApiResponse(responseCode = "200", description = "Author was changed")
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Author ID to change")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Author to change", required = true, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))
    })
    @PatchMapping("/change/{id}")
    public HttpStatus changeAuthor(@PathVariable int id, @RequestBody Author author) {
        authorService.changeAuthor(id, author);
        return HttpStatus.OK;
    }

}
