package br.com.ada.reactivejavasalarymanagement.repository;

import br.com.ada.reactivejavasalarymanagement.model.Salary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SalaryRepository extends ReactiveMongoRepository<Salary, String> {

    Mono<Salary> findByCode(String code);

    Mono<Salary> deleteByCode(String code);
}
