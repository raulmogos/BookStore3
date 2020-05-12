package ro.bookstore3.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.bookstore3.client.ui.Console;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("start client app");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ro/bookstore3/client/config");

        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        context.getBean(Console.class).run(restTemplate);

        System.out.println("client stopped");
    }
}
