package br.com.ada.reactivejavaclientmanagement.repository;

import br.com.ada.reactivejavaclientmanagement.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

    Mono<Client> findById(String id);

    Mono<Void> deleteById(String id);
}
