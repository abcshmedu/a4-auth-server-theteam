package edu.hm.huberneumeier.shareit.media.logic;

import edu.hm.huberneumeier.shareit.media.data.MediaPersistenceImpl;
import edu.hm.huberneumeier.shareit.media.logic.helpers.Utils;
import edu.hm.huberneumeier.shareit.media.media.Book;
import edu.hm.huberneumeier.shareit.media.media.Disc;
import edu.hm.huberneumeier.shareit.media.media.Medium;
import uk.co.moreofless.ISBNValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a media service.
 * Methods to create, read and update different media types.
 *
 * @author Tobias Huber
 * @author Andreas Neumeier
 * @version 2017-04-26
 */
public class MediaServiceImpl implements MediaService {
    /**
     * List of all media items.
     */
    private final List<Medium> arrayList = new ArrayList<>();
    private final MediaPersistenceImpl mediaPersistence = new MediaPersistenceImpl();

    /**
     * Default constructor.
     */
    public MediaServiceImpl() {
    }

    @Override
    public MediaServiceResult addBook(Book book) {
        //clear isbn from unnecessary characters like - or spaces
        book.clearISBN();

        //check if all parameters necessary are set
        if (!ISBNValidator.validateISBN13(book.getIsbn()) || book.getAuthor().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.BAD_REQUEST;
        }

        //check if book exists
        if (mediaPersistence.getBook(book.getIsbn()) != null) {
            return MediaServiceResult.BAD_REQUEST;
        }

        //add book if there wasn't an error
        mediaPersistence.saveMedia(book);
        return MediaServiceResult.CREATED;
    }

    @Override
    public Book getBook(String isbn) {
        return mediaPersistence.getBook(isbn);

        ////return (Book) currentSession.createQuery("FROM Book WHERE isbn like '" + isbn + "'").list().get(0);
        //Medium[] mediaArray = Utils.getMediaOfType(arrayList, Book.class);
        //for (Medium book : mediaArray) {
        //    Book current = (Book) book;
        //    if (current.getIsbn().equals(isbn)) {
        //        return current;
        //    }
        //}
        //return null;
    }

    @Override
    public Medium[] getBooks() {
        List<Book> books = mediaPersistence.getBooks();
        Medium[] media = new Medium[books.size()];
        for (int i = 0; i < books.size(); i++) {
            media[i] = books.get(i);
        }
        return media;
    }

    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {
        //isbn cant be changed
        if (!isbn.equals(book.getIsbn())) {
            return MediaServiceResult.BAD_REQUEST;
        }

        //min one value must be set
        if (book.getTitle() == null && book.getAuthor() == null) {
            return MediaServiceResult.BAD_REQUEST;
        }

        Book result = mediaPersistence.getBook(book.getIsbn());

        //if no book with isbn found end otherwise create the new book we will store
        Book newBook;
        if (result == null) {
            return MediaServiceResult.NOT_FOUND;
        } else if (book.getTitle() == null) {
            //not all information needed for updating so we need to set the missing values with the old once
            newBook = new Book(result.getTitle(), book.getAuthor(), book.getIsbn());
        } else if (book.getAuthor() == null) {
            //not all information needed for updating so we need to set the missing values with the old once
            newBook = new Book(book.getTitle(), result.getAuthor(), book.getIsbn());
        } else {
            newBook = book;
        }

        if (result.getTitle().equals(newBook.getTitle()) && result.getAuthor().equals(newBook.getAuthor())) {
            return MediaServiceResult.NOT_MODIFIED;
        }

        mediaPersistence.updateMedia(newBook);

        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        //clear barcode
        disc.clearBarcode();

        if (!Utils.validateBarcode(disc.getBarcode()) || disc.getDirector() == null || disc.getTitle() == null) {
            return MediaServiceResult.BAD_REQUEST;
        }

        if (mediaPersistence.getDisc(disc.getBarcode()) != null) {
            return MediaServiceResult.BAD_REQUEST;
        }

        mediaPersistence.saveMedia(disc);
        return MediaServiceResult.CREATED;
    }

    @Override
    public Disc getDisc(String barcode) {
        return mediaPersistence.getDisc(barcode);
    }

    @Override
    public Medium[] getDiscs() {
        List<Disc> discs = mediaPersistence.getDiscs();
        Medium[] media = new Medium[discs.size()];
        for (int i = 0; i < discs.size(); i++) {
            media[i] = discs.get(i);
        }
        return media;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        if (!barcode.equals(disc.getBarcode())) {
            return MediaServiceResult.BAD_REQUEST;
        }

        if (disc.getTitle() == null && disc.getDirector() == null && disc.getFsk() == null) {
            return MediaServiceResult.BAD_REQUEST;
        }

        Disc result = mediaPersistence.getDisc(disc.getBarcode());

        //if no disc with barcode found end otherwise create the new disc we will store
        if (result == null) {
            return MediaServiceResult.NOT_FOUND;
        }

        //not all information needed for updating so we need to set the missing values with the old once
        Disc newDisc = disc;
        if (disc.getDirector() == null) {
            newDisc = new Disc(disc.getBarcode(), result.getDirector(), newDisc.getFsk(), newDisc.getTitle());
        }
        if (disc.getFsk() == null) {
            newDisc = new Disc(disc.getBarcode(), newDisc.getDirector(), result.getFsk(), newDisc.getTitle());
        }
        if (disc.getTitle() == null) {
            newDisc = new Disc(disc.getBarcode(), newDisc.getDirector(), newDisc.getFsk(), result.getTitle());
        }

        if (result.getTitle().equals(newDisc.getTitle()) && result.getDirector().equals(newDisc.getDirector()) && result.getFsk() == newDisc.getFsk()) {
            return MediaServiceResult.NOT_MODIFIED;
        }

        mediaPersistence.updateMedia(newDisc);

        return MediaServiceResult.ACCEPTED;
    }
}
