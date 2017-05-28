package edu.hm.huberneumeier.shareit.auth.media.jsonMappings;

/**
 * Used to map json authentication requests from the authentication api.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class UnauthenticatedUser {
    private String username;
    private String password;

    /**
     * Instantiates a new Unauthenticated user.
     */
    public UnauthenticatedUser() {

    }

    /**
     * Instantiates a new Unauthenticated user.
     *
     * @param username the username
     * @param password the password
     */
    public UnauthenticatedUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
