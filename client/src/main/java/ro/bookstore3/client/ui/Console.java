package ro.bookstore3.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    @Autowired
    private UiBooks uiBooks;
    @Autowired
    private UiClients uiClients;
    @Autowired
    private UiPurchases uiPurchases;

    public void run(RestTemplate restTemplate) {
        uiBooks.setRestTemplate(restTemplate);
        uiClients.setRestTemplate(restTemplate);
        uiPurchases.setRestTemplate(restTemplate);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("\n");
            System.out.println("Menu:");
            System.out.println("0 - Exit\n");
            System.out.println("1 - Add Book");
            System.out.println("2 - Add Client");
            System.out.println("3 - Add Purchase - Buy a book\n");
            System.out.println("4 - List Books");
            System.out.println("5 - List Clients");
            System.out.println("6 - List Purchases\n");
            System.out.println("7 - Update Book");
            System.out.println("8 - Update Client\n");
            System.out.println("9 - Delete Book");
            System.out.println("10 - Delete Client");
            System.out.println("11 - Delete Purchase\n");
            System.out.println("12 - Filter Books by Author");
            System.out.println("13 - Filter Books by Price");
            System.out.println("14 - Filter Clients by Name\n");
            System.out.println("15 - Top 10 clients on money spent");
            try {
                String choice = reader.readLine();
                int intChoice = Integer.parseInt(choice);
                switch (intChoice) {
                    case 0:
                        return;
                    case 1:
                        uiBooks.addBook();
                        break;
                    case 2:
                        uiClients.addClient();
                        break;
                    case 3:
                        uiPurchases.addPurchase();
                        break;
                    case 4:
                        uiBooks.printBooks();
                        break;
                    case 5:
                        uiClients.printClients();
                        break;
                    case 6:
                        uiPurchases.printPurchases();
                        break;
                    case 7:
                        uiBooks.updateBook();
                        break;
                    case 8:
                        uiClients.updateClient();
                        break;
                    case 9:
                        uiBooks.deleteBook();
                        break;
                    case 10:
                        uiClients.deleteClient();
                        break;
                    case 11:
                        uiPurchases.deletePurchase();
                        break;
                    case 12:
                        uiBooks.filterBooksAuthor();
                        break;
                    case 13:
                        uiBooks.filterBooksPrice();
                        break;
                    case 14:
                        uiClients.filterClientsName();
                        break;
                    case 15:
                        uiClients.filterTopClients();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
