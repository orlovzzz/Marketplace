package com.example.controller;

import com.example.entity.Book;
import com.example.entity.WashingMachine;
import com.example.service.WashingMachineService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Washing Machine Controller", description = "Washing machine API")
@RestController
@Getter
@RequestMapping("/wash")
public class WashingMachineController {

    @Autowired
    private WashingMachineService washingMachineService;

    private Gson gson = new Gson();

    @Operation(summary = "Get all washing machine", responses = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = WashingMachine.class))
                            )
                    }
            )
    })
    @GetMapping("")
    public List<WashingMachine> getAllWashingMachines() {
        return washingMachineService.getAllWashingMachines();
    }

    @Operation(summary = "Get washing machine by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Washing machine was found",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WashingMachine.class)
                            )
                    }
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Washing machine ID for searching")
    @GetMapping("/{id}")
    public WashingMachine getWashingMachineById(@PathVariable int id) {
        WashingMachine washingMachine = washingMachineService.getWashingMachineById(id);
        return washingMachine;
    }

    @Operation(summary = "Add washing machine", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Washing machine was added"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Washing machine to add", required = true, content = {
                    @Content(
                            schema = @Schema(implementation = WashingMachine.class)
                    )
    }
    )
    @PostMapping("/add")
    public HttpStatus addWashingMachine(@RequestBody WashingMachine washingMachine) {
        washingMachineService.addWashingMachine(washingMachine);
        return HttpStatus.OK;
    }

    @Operation(summary = "Delete washing machine", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Washing machine was deleted"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Washing machine ID to delete")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteWashingMachine(@PathVariable int id) {
        washingMachineService.deleteWashingMachine(id);
        return HttpStatus.OK;
    }

    @Operation(summary = "Change washing machine", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Washing machine was changed"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Washing machine ID to change")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Washing machine to change", required = true, content = {
            @Content(
                    schema = @Schema(implementation = WashingMachine.class)
            )
    }
    )
    @PatchMapping("/change/{id}")
    public HttpStatus changeClient(@PathVariable int id, @RequestBody WashingMachine washingMachine) {
        washingMachineService.changeWashingMachine(id, washingMachine);
        return HttpStatus.OK;
    }

}
