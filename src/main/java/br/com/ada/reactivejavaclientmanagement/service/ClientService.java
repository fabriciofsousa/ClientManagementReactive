package br.com.ada.reactivejavaclientmanagement.service;

import br.com.ada.reactivejavaclientmanagement.converter.ClientConverter;
import br.com.ada.reactivejavaclientmanagement.dto.ClientDTO;
import br.com.ada.reactivejavaclientmanagement.dto.ResponseDTO;
import br.com.ada.reactivejavaclientmanagement.model.Client;
import br.com.ada.reactivejavaclientmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ClientService {

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ClientRepository clientRepository;

    public Mono<ResponseDTO> create(ClientDTO clientDTO){
        Client client = this.clientConverter.toClient(clientDTO);
        Mono<Client> clientMono = clientRepository.save(client);

        return clientMono.map(clientDocument ->
                new ResponseDTO("Cliente cadastrado com sucesso",
                this.clientConverter.toClientDTO(clientDocument),
                LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar o Cliente abaixo",
                        clientDTO,
                LocalDateTime.now()));

    }

    public Flux<ResponseDTO<ClientDTO>> getAll(){
        Flux<Client> clientMono = clientRepository.findAll();

        return clientMono.map(clientDocument ->
                new ResponseDTO("Listagem de clientes com sucesso",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()));

    }

    public Mono<ResponseDTO> findById(String id){
        Mono<Client> clienteMono = clientRepository.findById(id);

        return clienteMono.map(clientDocument ->
                new ResponseDTO("Cliente encontrado com sucesso",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()));

    }

    public Mono<ResponseDTO> update(ClientDTO clientDTO) {
        Mono<Client> clientMono = this.clientRepository.findById(clientDTO.getId());
        return clientMono.flatMap(
                (existingClient) ->{
                    existingClient.setId(clientDTO.getId());
                    existingClient.setAge(clientDTO.getAge());
                    existingClient.setName(clientDTO.getName());
                    existingClient.setEmail(clientDTO.getEmail());
                    return this.clientRepository.save(existingClient);
                })

                .map(
                        client ->
                                new ResponseDTO<>("Cliente alterado com sucesso!",
                this.clientConverter.toClientDTO(client),
                LocalDateTime.now()));
    }

    public Mono<ResponseDTO> delete(String code) {
        return this.clientRepository
                .deleteById(code).map((client) -> new ResponseDTO<>("Client removido com sucesso!",
                        null,
                        LocalDateTime.now()));
    }

}
