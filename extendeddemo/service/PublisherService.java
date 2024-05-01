package extendeddemo.service;

import extendeddemo.dao.PublisherI;
import extendeddemo.model.Publisher;
import extendeddemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PublisherService implements PublisherI {

    @Override
    public void createPublisher(Publisher publisher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Publisher existingPublisher = session.get(Publisher.class, publisher.getId());
            if (existingPublisher == null) {
                session.save(publisher); // No existing record, create a new one
            } else {
                existingPublisher.setName(publisher.getName()); // Update existing record with new name
                existingPublisher.setBooks(publisher.getBooks()); // Update books if needed
                session.saveOrUpdate(existingPublisher); // Save updated publisher
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
    public List<Publisher> getAllPublishers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Publisher", Publisher.class).list();
        }
    }

    @Override
    public Publisher getPublisherById(int publisherId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Publisher.class, publisherId);
        }
    }

    public void createOrUpdatePublisher(Publisher publisher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Publisher existingPublisher = session.get(Publisher.class, publisher.getId());
            if (existingPublisher == null) {
                session.save(publisher);
            } else {
                existingPublisher.setName(publisher.getName());
                session.saveOrUpdate(existingPublisher);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
