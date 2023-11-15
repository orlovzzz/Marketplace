package com.example.controller;

import com.example.entity.Book;
import com.example.entity.Telephone;
import com.example.service.TelephoneService;
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
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Telephone Controller", description = "Telephone API")
@RestController
@Getter
@RequestMapping("/telephone")
public class TelephoneController {

    private Gson gson = new Gson();

    @Autowired
    private TelephoneService telephoneService;

    @Operation(summary = "Get all telephones", responses = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Telephone.class))
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public List<Telephone> getAllTelephones() {
        return telephoneService.getAllTelephones();
    }

    @Operation(summary = "Get telephone by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telephone was found",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Telephone.class)
                            )
                    }
            )
    })
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Telephone ID for searching")
    @GetMapping("/{id}")
    public Telephone getTelephoneById(@PathVariable int id) {
        Telephone telephone = telephoneService.getTelephoneById(id);
        return telephone;
    }

    @Operation(summary = "Add telephone", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telephone was added"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Telephone to add", required = true, content = {
            @Content(
                    schema = @Schema(implementation = Telephone.class)
            )
    }
    )
    @PostMapping("/add")
    @PreAuthorize("hasRole('SELLER')")
    public HttpStatus addTelephone(@RequestBody Telephone telephone) {
        telephoneService.addTelephone(telephone);
        return HttpStatus.OK;
    }

    @Operation(summary = "Delete telephone", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telephone was deleted"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Telephone ID to delete")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public HttpStatus deleteTelephone(@PathVariable int id) {
        telephoneService.deleteTelephone(id);
        return HttpStatus.OK;
    }

    @Operation(summary = "Change telephone", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telephone was changed"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Telephone ID to change")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Telephone to change", required = true, content = {
            @Content(
                    schema = @Schema(implementation = Telephone.class)
            )
    }
    )
    @PatchMapping("/change/{id}")
    public HttpStatus changeClient(@PathVariable int id, @RequestBody Telephone telephone) {
        telephoneService.changeTelephone(id, telephone);
        return HttpStatus.OK;
    }

}
