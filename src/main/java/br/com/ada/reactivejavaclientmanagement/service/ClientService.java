package br.com.ada.reactivejavaclientmanagement.service;

import br.com.ada.reactivejavaclientmanagement.converter.ClientConverter;
import br.com.ada.reactivejavaclientmanagement.dto.ClientDTO;
import br.com.ada.reactivejavaclientmanagement.dto.ResponseDTO;
import br.com.ada.reactivejavaclientmanagement.model.Client;
import br.com.ada.reactivejavaclientmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ClientRepository clientRepository;

    public Optional<ResponseDTO> create(ClientDTO clientDTO){
        Client client = this.clientConverter.toClient(clientDTO);
        Optional<Client> clientMono = Optional.of(clientRepository.save(client));

        return Optional.of(clientMono.map(clientDocument ->
                        new ResponseDTO("Cliente cadastrado com sucesso",
                                this.clientConverter.toClientDTO(clientDocument),
                                LocalDateTime.now()))
                .orElse(new ResponseDTO("Erro ao cadastrar o Cliente abaixo",
                        clientDTO,
                        LocalDateTime.now())));

    }

    public Optional<List<Client>> getAll(){
        return Optional.of(clientRepository.findAll());
    }

    public Optional<ResponseDTO> findById(String id){
        Optional<Client> cliente = clientRepository.findById(id);

        return cliente.map(clientDocument ->
                new ResponseDTO("Cliente encontrado com sucesso",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()));

    }

    public Optional<ResponseDTO<ClientDTO>> update(ClientDTO clientDTO) {
        Optional<Object> client = Optional.ofNullable(this.clientRepository.findById(clientDTO.getId()));
        return client.map(
                (existingClient) ->{
                    Client cliente = new Client(clientDTO.getId(),
                            clientDTO.getAge(),
                            clientDTO.getName(),
                            clientDTO.getEmail());

                    return this.clientRepository.save(cliente);
                })

                .map(
                        clienteSucesso ->
                                new ResponseDTO<>("Cliente alterado com sucesso!",
                this.clientConverter.toClientDTO(clienteSucesso),
                LocalDateTime.now()));
    }

    public void delete(String code) {
        this.clientRepository.deleteById(code);
    }

}
