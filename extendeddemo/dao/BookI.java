package extendeddemo.dao;


import extendeddemo.model.Book;
import java.util.List;

public interface BookI {
    void createBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
}
