package ro.bookstore3.client.ui;


import org.springframework.web.client.RestTemplate;
import ro.bookstore3.models.Purchase;
import ro.bookstore3.web.dto.PurchaseDto;
import ro.bookstore3.web.dto.PurchasesDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UiPurchases {
    private static final String URL = "http://localhost:8080/api/purchases";
    private RestTemplate restTemplate;

    public void addPurchase() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Book ID:");
            String bookID = reader.readLine();
            System.out.println("Client ID:");
            String clientID = reader.readLine();
            PurchaseDto dto = restTemplate.postForObject(URL, new Purchase(Long.parseLong(bookID), Long.parseLong(clientID)), PurchaseDto.class);
            System.out.println("Purchase added successfully: ");
            System.out.println(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPurchases() {
        PurchasesDto purchases = restTemplate.getForObject(URL, PurchasesDto.class);
        if (purchases != null) {
            purchases.getPurchases().forEach(System.out::println);
        }
        else {
            System.out.println("No purchases found");
        }
    }

    public void deletePurchase() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Purchase ID:");
            String ID = reader.readLine();
            restTemplate.delete(URL + "/{id}", Long.parseLong(ID));
            System.out.println("Purchase deleted successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
