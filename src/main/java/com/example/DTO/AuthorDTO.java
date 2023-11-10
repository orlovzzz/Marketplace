package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class AuthorDTO {
    @JsonProperty("id")
    @Schema(example = "1")
    private int id;
}
