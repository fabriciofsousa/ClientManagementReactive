package br.com.ada.reactivejavaclientmanagement.converter;

import br.com.ada.reactivejavaclientmanagement.dto.ClientDTO;
import br.com.ada.reactivejavaclientmanagement.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientDTO toClientDTO(Client client){
        return new ClientDTO(client.getId(), client.getName(), client.getAge(), client.getEmail());
    }


    public Client toClient(ClientDTO client){
        return new Client(client.getId(), client.getName(), client.getAge(), client.getEmail());
    }
}
