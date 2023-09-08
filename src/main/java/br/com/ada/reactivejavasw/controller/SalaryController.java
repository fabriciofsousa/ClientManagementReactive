package br.com.ada.reactivejavasw.controller;

import br.com.ada.reactivejavasw.dto.SalaryDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "salary")
@AllArgsConstructor
public class SalaryController {

    private SalaryService salaryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a salary", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(SalaryDTO salaryDTO){
        return salaryService.create(salaryDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all salaries")
    public Flux<ResponseDTO<SalaryDTO>> getAll(){
        return salaryService.getAll();
    }

    @GetMapping("{code}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Get by code")
    public Mono<ResponseDTO> findByCode(@PathVariable("code") String code){
        return salaryService.findByCode(code);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a salary")
    public Mono<ResponseDTO> update(@RequestBody SalaryDTO salaryDTO){
        return this.salaryService.update(salaryDTO);
    }

    @DeleteMapping("{code}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseDTO> delete(@PathVariable("code") String code) {
        return this.salaryService.delete(code);
    }


}
