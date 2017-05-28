package edu.hm.huberneumeier.shareit.auth.media;

import java.util.HashMap;
import java.util.Map;

/**
 * Containing all authorizations available. For each media operation one.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public enum Authorisation {
    /**
     * Media service results.
     */
    BOOK_CREATE(1, "media.book.create", "Your are allowed to create a new book"),
    /**
     * The Book read.
     */
    BOOK_READ(2, "media.book.read", "You are allowed to get a book."),
    /**
     * The Book update.
     */
    BOOK_UPDATE(3, "media.book.update", "Your are allowed to update a book."),
    /**
     * The Book delete.
     */
    BOOK_DELETE(4, "media.book.delete", "You are allowed to delete a book."),
    /**
     * The Disc create.
     */
    DISC_CREATE(5, "media.disc.create", "Your are allowed to create a new disc"),
    /**
     * The Disc read.
     */
    DISC_READ(6, "media.disc.read", "You are allowed to get a disc."),
    /**
     * The Disc update.
     */
    DISC_UPDATE(7, "media.disc.update", "Your are allowed to update a disc."),
    /**
     * The Disc delete.
     */
    DISC_DELETE(8, "media.disc.delete", "You are allowed to delete a disc.");

    private static Map<Integer, Authorisation> map = new HashMap<Integer, Authorisation>();

    static {
        for (Authorisation elem : Authorisation.values()) {
            map.put(elem.getId(), elem);
        }
    }

    private final int id;
    private final String name;
    private final String description;

    /**
     * Constructor of authorisation.
     *
     * @param id          the id of the authorisation (unique)
     * @param name        the name of the authorisation
     * @param description the description to the authorisation
     */
    Authorisation(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Value of authorisation.
     *
     * @param id the id
     * @return the authorisation
     */
    public static Authorisation valueOf(int id) {
        return map.get(id);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
