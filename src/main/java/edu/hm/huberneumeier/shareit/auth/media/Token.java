package edu.hm.huberneumeier.shareit.auth.media;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a token that could be used for authorisation.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 28.05.2017
 */
@Entity
public class Token {
    private static final int LENGTH = 128;
    //token should be valid for 15 minutes
    private static final int VALID_TIME = 900000;
    private static Map<String, Token> createdKeys = new HashMap<>();
    @Id
    private String key;
    private long created;
    private long validUntil;

    /**
     * Instantiates a new Token.
     */
    public Token() {
        this.key = createKey();
        this.created = System.currentTimeMillis();
        this.validUntil = System.currentTimeMillis() + VALID_TIME;

        createdKeys.put(this.key, this);
    }

    /**
     * Gets token to key.
     *
     * @param key the key
     * @return the token to key
     */
    public static Token getTokenToKey(String key) {
        if (createdKeys.containsKey(key))
            return createdKeys.get(key);
        return null;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public long getCreated() {
        return created;
    }

    /**
     * Gets valid until.
     *
     * @return the valid until
     */
    public long getValidUntil() {
        return validUntil;
    }

    /**
     * Create a new unique key.
     *
     * @return
     */
    private String createKey() {
        String newKey = RandomStringUtils.randomAlphanumeric(LENGTH);
        while (newKey.isEmpty() && createdKeys.containsKey(newKey))
            newKey = RandomStringUtils.randomAlphanumeric(LENGTH);
        return newKey;
    }
}
