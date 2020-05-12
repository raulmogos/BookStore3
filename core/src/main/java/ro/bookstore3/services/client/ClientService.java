package ro.bookstore3.services.client;

import ro.bookstore3.models.Client;

import java.util.List;

public interface ClientService {

    String NAME = "ClientService";

    Client getClientById(Long id);

    Client addClient(Client client);

    void removeClient(Long id);

    Client updateClient(Client client);

    List<Client> getAllClients();

    List<Client> filterClientsByName(String name);

    List<Client> topNClientsOnMoneySpent(int n);
}