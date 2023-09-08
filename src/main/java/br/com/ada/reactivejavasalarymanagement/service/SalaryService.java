package br.com.ada.reactivejavasalarymanagement.service;

import br.com.ada.reactivejavasalarymanagement.converter.SalaryConverter;
import br.com.ada.reactivejavasalarymanagement.dto.SalaryDTO;
import br.com.ada.reactivejavasalarymanagement.dto.ResponseDTO;
import br.com.ada.reactivejavasalarymanagement.model.Salary;
import br.com.ada.reactivejavasalarymanagement.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class SalaryService {

    @Autowired
    private SalaryConverter salaryConverter;

    @Autowired
    private SalaryRepository salaryRepository;

    public Mono<ResponseDTO> create(SalaryDTO salaryDTO){
        Salary salary = this.salaryConverter.toSalary(salaryDTO);
        Mono<Salary> salaryMono = salaryRepository.save(salary);

        return salaryMono.map(salaryDocument ->
                new ResponseDTO("Salário cadastrado com sucesso",
                this.salaryConverter.toSalaryDTO(salaryDocument),
                LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar o salário abaixo",
                salaryDTO,
                LocalDateTime.now()));

    }

    public Flux<ResponseDTO<SalaryDTO>> getAll(){
        Flux<Salary> salaryMono = salaryRepository.findAll();

        return salaryMono.map(salaryDocument ->
                new ResponseDTO("Listagem de salários com sucesso",
                        this.salaryConverter.toSalaryDTO(salaryDocument),
                        LocalDateTime.now()));

    }

    public Mono<ResponseDTO> findByCode(String code){
        Mono<Salary> salaryMono = salaryRepository.findByCode(code);

        return salaryMono.map(salaryDocument ->
                new ResponseDTO("Salário encontrado com sucesso",
                        this.salaryConverter.toSalaryDTO(salaryDocument),
                        LocalDateTime.now()));

    }

    public Mono<ResponseDTO> update(SalaryDTO salaryDTO) {
        Mono<Salary> salaryMono = this.salaryRepository.findByCode(salaryDTO.getCode());
        return salaryMono.flatMap(
                (existingSalary) ->{
                    existingSalary.setCode(salaryDTO.getCode());
                    existingSalary.setIdade(salaryDTO.getIdade());
                    existingSalary.setEscolaridade(salaryDTO.getEscolaridade());
                    existingSalary.setCargo(salaryDTO.getCargo());
                    existingSalary.setGenero(salaryDTO.getGenero());
                    existingSalary.setSalario(salaryDTO.getSalario());
                    existingSalary.setTempoDeExperienciaEmAnos(salaryDTO.getTempoDeExperienciaEmAnos());
                    return this.salaryRepository.save(existingSalary);
                })

                .map(
                        salary ->
                                new ResponseDTO<>("Salário alterado com sucesso!",
                this.salaryConverter.toSalaryDTO(salary),
                LocalDateTime.now()));
    }

    public Mono<ResponseDTO> delete(String code) {
        return this.salaryRepository
                .deleteByCode(code).map((salary) -> new ResponseDTO<>("Salário removido com sucesso!",
                        null,
                        LocalDateTime.now()));
    }

}
