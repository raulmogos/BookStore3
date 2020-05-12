package ro.bookstore3.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.bookstore3.client.ui.Console;
import ro.bookstore3.client.ui.UiBooks;
import ro.bookstore3.client.ui.UiClients;
import ro.bookstore3.client.ui.UiPurchases;

@Configuration
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UiBooks uiBooks() {
        return new UiBooks();
    }

    @Bean
    UiClients uiClients() {
        return new UiClients();
    }

    @Bean
    UiPurchases uiPurchases() {
        return new UiPurchases();
    }

    // console

    @Bean
    Console console() {
        return new Console();
    }
}
