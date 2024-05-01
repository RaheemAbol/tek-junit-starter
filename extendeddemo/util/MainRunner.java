package extendeddemo.util;

import extendeddemo.model.Book;
import extendeddemo.model.Publisher;
import extendeddemo.service.BookService;
import extendeddemo.service.PublisherService;

public class MainRunner {

    public static void main(String[] args) {
        initDummyData();
    }

    private static void initDummyData() {
        BookService bookService = new BookService();
        PublisherService publisherService = new PublisherService();

        // Create publishers and books
        Publisher pub1 = new Publisher(1, "Publisher One");
        Publisher pub2 = new Publisher(2, "Publisher Two");
        Book book1 = new Book(1, "Java Fundamentals");
        Book book2 = new Book(2, "Advanced Java");

        // Since IDs are manually assigned, check for existing entities before adding
        if (publisherService.getPublisherById(pub1.getId()) == null) {
            publisherService.createPublisher(pub1);
        }
        if (publisherService.getPublisherById(pub2.getId()) == null) {
            publisherService.createPublisher(pub2);
        }
        if (bookService.getBookById(book1.getId()) == null) {
            bookService.createBook(book1);
        }
        if (bookService.getBookById(book2.getId()) == null) {
            bookService.createBook(book2);
        }

        // Link books and publishers
        pub1.addBook(book1);
        pub1.addBook(book2);
        pub2.addBook(book2);

        // Save updates (if not using cascading saves, this might be unnecessary)
        publisherService.createOrUpdatePublisher(pub1);
        publisherService.createOrUpdatePublisher(pub2);

        System.out.println("Dummy data initialized!");
    }
}
