package edu.hm.huberneumeier.shareit.media.data;

import edu.hm.huberneumeier.shareit.media.media.Book;
import edu.hm.huberneumeier.shareit.media.media.Disc;
import edu.hm.huberneumeier.shareit.media.media.Medium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Description.
 *
 * @author Andreas Neumeier
 * @version 2017 -06-07
 */
public class MediaPersistenceImpl implements MediaPersistence {
    private static SessionFactory sessionFactory;

    /**
     * Instantiates a new Media persistence.
     */
    public MediaPersistenceImpl() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            //TODO add log4j outprint
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Gets book.
     *
     * @param isbn the isbn
     * @return the book
     */
    public Book getBook(String isbn) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.createQuery("FROM Book WHERE isbn like '" + isbn + "'").list().get(0);
        session.close();
        return book;
    }

    /**
     * Gets books.
     *
     * @return the books
     */
    public List<Book> getBooks() {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("from Book").list();
        session.close();
        return books;
    }

    /**
     * Gets disc.
     *
     * @param barcode the barcode
     * @return the disc
     */
    public Disc getDisc(String barcode) {
        Session session = sessionFactory.openSession();
        Disc disc = (Disc) session.createQuery("FROM Disc WHERE barcode like '" + barcode + "'").list().get(0);
        session.close();
        return disc;
    }

    /**
     * Gets discs.
     *
     * @return the discs
     */
    public List<Disc> getDiscs() {
        Session session = sessionFactory.openSession();
        List<Disc> discs = session.createQuery("from Disc").list();
        session.close();
        return discs;
    }

    /**
     * Save media.
     *
     * @param medium the medium
     */
    public void saveMedia(Medium medium) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(medium);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Update media.
     *
     * @param medium the medium
     */
    public void updateMedia(Medium medium) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(session.merge(medium));
        session.close();
    }
}
