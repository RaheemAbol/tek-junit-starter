package extendeddemo.service;

import extendeddemo.dao.BookI;
import extendeddemo.model.Book;
import extendeddemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookService implements BookI {

    @Override
    public void createBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book existingBook = session.get(Book.class, book.getId());
            if (existingBook == null) {
                session.save(book); // No existing record, create a new one
            } else {
                existingBook.setTitle(book.getTitle()); // Update existing record with new title
                existingBook.setPublishers(book.getPublishers()); // Update publishers if needed
                session.saveOrUpdate(existingBook); // Save updated book
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    @Override
    public Book getBookById(int bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, bookId);
        }
    }
}
