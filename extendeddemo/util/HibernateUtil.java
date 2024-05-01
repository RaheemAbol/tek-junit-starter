package extendeddemo.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            // Assuming 'hibernate.cfg.xml' includes settings for the Book and Publisher entities
            return new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void rollbackTransaction() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction tx = session.getTransaction();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    // Clear database (assuming you have a way to clear or reset it)
    public static void clearDatabase() {
        // Depending on your DB, this could be executing a series of DELETE FROM statements
        // or TRUNCATE statements, etc.
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE book RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE publisher RESTART IDENTITY CASCADE").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

