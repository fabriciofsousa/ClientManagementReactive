package br.com.ada.reactivejavaclientmanagement.repository;

import br.com.ada.reactivejavaclientmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findById(String id);

    void deleteById(String id);
}
