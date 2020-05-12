package ro.bookstore3.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.bookstore3.models.Client;
import ro.bookstore3.services.client.ClientService;
import ro.bookstore3.web.converter.ClientConverter;
import ro.bookstore3.web.dto.ClientDto;
import ro.bookstore3.web.dto.ClientsDto;

import java.util.stream.Collectors;

@RestController
public class ClientController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDto getClients() {
        //todo: log
        return new ClientsDto(clientConverter
                .convertModelsToDtos(clientService.getAllClients()));

    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    ClientDto saveClient(@RequestBody ClientDto clientDto) {
        //todo log
        return clientConverter.convertModelToDto(clientService.addClient(
                clientConverter.convertDtoToModel(clientDto)
        ));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id,
                       @RequestBody ClientDto clientDto) {
        //todo: log
        Client book = clientConverter.convertDtoToModel(clientDto);
        return clientConverter.convertModelToDto(clientService.updateClient(new Client(id,
                book.getFirstName(), book.getLastName(), book.getMoneySpent())));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        //todo:log

        clientService.removeClient(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/{name}", method = RequestMethod.GET)
    ClientsDto filterClientName(@PathVariable String name) {
        //todo:Log
        return new ClientsDto(clientConverter
                .convertModelsToDtos(clientService.getAllClients()
                        .stream()
                        .filter(client -> client.getFirstName().contains(name) || client.getLastName().contains(name))
                        .collect(Collectors.toList())));
    }

    @RequestMapping(value = "/clients/{number}", method = RequestMethod.GET)
    ClientsDto filterTopMoneySpentClient(@PathVariable int number) {
        //todo:Log
        return new ClientsDto(clientConverter
                .convertModelsToDtos(clientService.getAllClients().
                        stream()
                        .limit(number)
                        .collect(Collectors.toList())));
    }
}
