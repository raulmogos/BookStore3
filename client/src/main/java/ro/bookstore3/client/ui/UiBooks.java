package ro.bookstore3.client.ui;

import org.springframework.web.client.RestTemplate;
import ro.bookstore3.models.Book;
import ro.bookstore3.web.dto.BookDto;
import ro.bookstore3.web.dto.BooksDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UiBooks {
    private static final String URL = "http://localhost:8080/api/books";
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addBook() {
        String title, author, price;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Book Title:");
            title = reader.readLine();
            System.out.println("Book Author:");
            author = reader.readLine();
            System.out.println("Book Price:");
            price = reader.readLine();
            BookDto dto = restTemplate.postForObject(URL, new BookDto(title, author, Integer.parseInt(price)), BookDto.class);
            System.out.println("Successfully added: ");
            System.out.println(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBooks() {
        BooksDto books = restTemplate.getForObject(URL, BooksDto.class);
        if (books != null) {
            books.getBooks().forEach(System.out::println);
        }
        else {
            System.out.println("No books found");
        }
    }

    public void updateBook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Book ID:");
            String id = reader.readLine();
            System.out.println("New Book Title (Blank if unchanged):");
            String title = reader.readLine();
            System.out.println("New Book Author (Blank if unchanged):");
            String author = reader.readLine();
            System.out.println("New Book Price (Blank if unchanged):");
            String price = reader.readLine();
            Book book = new Book(Long.parseLong(id), title, author, Double.parseDouble(price));
            restTemplate.put(URL + "/{id}", book, Long.parseLong(id));
            System.out.println("Updated successfully !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Book id:");
            String id = reader.readLine();
            restTemplate.delete(URL + "/{id}", Long.parseLong(id));
            System.out.println("Book deleted successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterBooksAuthor() {
        String author;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Book Author:");
            author = reader.readLine();
            BooksDto books = restTemplate.getForObject(URL + "/{author}", BooksDto.class, author);
            if (books != null) {
                books.getBooks().forEach(System.out::println);
            }
            else {
                System.out.println("No books found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterBooksPrice() {
        String minPrice, maxPrice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Minimum Price:");
            minPrice = reader.readLine();
            System.out.println("Maximum Price:");
            maxPrice = reader.readLine();
            BooksDto books = restTemplate.getForObject(
                    URL + "/{minPrice}/{maxPrice}",
                    BooksDto.class,
                    Double.parseDouble(minPrice),
                    Double.parseDouble(maxPrice));
            if (books != null) {
                books.getBooks().forEach(System.out::println);
            }
            else {
                System.out.println("No books found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
