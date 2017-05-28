package edu.hm.huberneumeier.shareit.auth.media;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a user object.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class User {
    private String username;
    private String password;
    private Date tokenExpires;
    private Token token;
    private AuthorisationGroup authorisationGroup;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this(username, password, AuthorisationGroup.USERS);
    }

    /**
     * Instantiates a new User.
     *
     * @param username           the username
     * @param password           the password
     * @param authorisationGroup the authorisation group
     */
    public User(String username, String password, AuthorisationGroup authorisationGroup) {
        this.username = username;
        this.password = password;
        this.authorisationGroup = authorisationGroup;
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public static Map<String, User> getUserList() {
        //create different users
        final Map<String, User> users = new HashMap();
        users.put("admin", new User("admin", "123456", AuthorisationGroup.ADMINS));
        users.put("user", new User("user", "123456"));
        return users;
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

    /**
     * Gets token.
     *
     * @return the token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Gets authorisation group.
     *
     * @return the authorisation group
     */
    public AuthorisationGroup getAuthorisationGroup() {
        return authorisationGroup;
    }

    /**
     * Sets authorisation group.
     *
     * @param authorisationGroup the authorisation group
     */
    public void setAuthorisationGroup(AuthorisationGroup authorisationGroup) {
        this.authorisationGroup = authorisationGroup;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }
}
