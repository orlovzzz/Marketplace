package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class BookDTO {

    @JsonProperty("number")
    @Schema(example = "+7123456789")
    private String number;

    @JsonProperty("price")
    @Schema(example = "100")
    private double price;

    @JsonProperty("name")
    @Schema(example = "Name")
    private String name;

    @JsonProperty("author")
    private AuthorDTO author;
}
