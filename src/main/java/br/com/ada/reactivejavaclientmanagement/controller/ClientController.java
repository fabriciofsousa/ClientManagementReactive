package br.com.ada.reactivejavaclientmanagement.controller;

import br.com.ada.reactivejavaclientmanagement.dto.ClientDTO;
import br.com.ada.reactivejavaclientmanagement.dto.ResponseDTO;
import br.com.ada.reactivejavaclientmanagement.model.Client;
import br.com.ada.reactivejavaclientmanagement.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Optional<ResponseDTO> create(ClientDTO clientDTO){
        return clientService.create(clientDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all clients")
    public Optional<List<Client>> getAll(){
        return clientService.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Get by id")
    public Optional<ResponseDTO> findById(@PathVariable("id") String code){
        return clientService.findById(code);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a client")
    public Optional<ResponseDTO<ClientDTO>> update(@RequestBody ClientDTO clientDTO){
        return this.clientService.update(clientDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
