package edu.hm.huberneumeier.shareit.auth.logic.authentication;

/**
 * Authentication implementation interface.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public interface AuthServiceExternal {
    
    /**
     * Authenticate a unauthenticated user by his username and password.
     *
     * @param username the username.
     * @param password the password.
     * @return the authentication result, was the authentication successful or not?
     */
    public AuthenticationResult authUser(String username, String password);
}
