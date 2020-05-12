package ro.bookstore3.client.ui;

import org.springframework.web.client.RestTemplate;
import ro.bookstore3.models.Client;
import ro.bookstore3.web.dto.ClientDto;
import ro.bookstore3.web.dto.ClientsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UiClients {
    private static final String URL = "http://localhost:8080/api/clients";
    private RestTemplate restTemplate;

    public void addClient() {
        String firstname, lastname;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("First Name:");
            firstname = reader.readLine();
            System.out.println("Last Name:");
            lastname = reader.readLine();
            ClientDto dto = restTemplate.postForObject(URL, new ClientDto(firstname, lastname, 0), ClientDto.class);
            System.out.println("Client added successfully: ");
            System.out.println(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printClients() {
        ClientsDto clients = restTemplate.getForObject(URL, ClientsDto.class);
        if (clients != null) {
            clients.getClients().forEach(System.out::println);
        }
        else {
            System.out.println("No clients found");
        }
    }

    public void updateClient() {
        String ID, firstname, lastname, spendings;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Client ID:");
            ID = reader.readLine();
            System.out.println("New First Name (Blank if unchanged):");
            firstname = reader.readLine();
            System.out.println("New Last Name (Blank if unchanged):");
            lastname = reader.readLine();
            System.out.println("New Spendings (Blank if unchanged):");
            spendings = reader.readLine();
            Client client = new Client(Long.parseLong(ID), firstname, lastname, Double.parseDouble(spendings));
            restTemplate.put(URL + "/{id}", client, Long.parseLong(ID));
            System.out.println("Client updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient() {
        String ID;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Client ID:");
            ID = reader.readLine();
            restTemplate.delete(URL + "/{id}", Long.parseLong(ID));
            System.out.println("Client deleted successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterClientsName() {
        String name;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Client Name:");
            name = reader.readLine();
            ClientsDto clients = restTemplate.getForObject(URL + "/{name}", ClientsDto.class, name);
            if (clients != null) {
                clients.getClients().forEach(System.out::println);
            }
            else {
                System.out.println("No clients found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterTopClients() {
        ClientsDto clients = restTemplate.getForObject(URL +"/{number}", ClientsDto.class, 10);
        if (clients != null) {
            clients.getClients().forEach(System.out::println);
        }
        else {
            System.out.println("No clients found");
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
