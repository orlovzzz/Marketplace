package com.example.controller;

import com.example.entity.Client;
import com.example.service.ClientService;
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

@Tag(name = "Client Controller", description = "Client API")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    private Gson gson = new Gson();

    @Operation(summary = "Get all clients", responses = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Client.class))
                            )
                    }
            )
    })
    @GetMapping("")
    public String getAllClients() {
        return gson.toJson(clientService.getAllClients());
    }

    @Operation(summary = "Get client by ID", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client was found",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Client.class)
                            )
                    }
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Client ID for searching")
    @GetMapping("/{id}")
    public String getClientById(@PathVariable int id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return gson.toJson(client);
        }
        JsonObject json = new JsonObject();
        json.addProperty("Error", "Client is missing");
        return json.toString();
    }

    @Operation(summary = "Add client", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client was added"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Client to add", required = true, content = {
            @Content(
                    schema = @Schema(implementation = Client.class)
            )
    }
    )
    @PostMapping("/add")
    public HttpStatus addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return HttpStatus.OK;
    }

    @Operation(summary = "Delete client", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client was deleted"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Client ID to delete")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return HttpStatus.OK;
    }

    @Operation(summary = "Change client", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client was changed"
            )
    })
    @Parameter(in = ParameterIn.PATH, name = "id", description = "Client ID to change")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Telephone to change", required = true, content = {
            @Content(
                    schema = @Schema(implementation = Client.class)
            )
    }
    )
    @PatchMapping("/change/{id}")
    public HttpStatus changeClient(@PathVariable int id, @RequestBody Client client) {
        clientService.changeClient(id, client);
        return HttpStatus.OK;
    }

}
