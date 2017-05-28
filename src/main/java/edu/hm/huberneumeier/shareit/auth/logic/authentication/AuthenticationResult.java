package edu.hm.huberneumeier.shareit.auth.logic.authentication;

import edu.hm.huberneumeier.shareit.auth.media.Token;

/**
 * Results of Authentications.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class AuthenticationResult {
    private AuthenticationState authenticationState;
    private String message;
    private Token token;

    /**
     * Constructor of a bad authentication result.
     *
     * @param authenticationState the state of the authentication
     * @param message             the message which should be transferred to the user.
     */
    public AuthenticationResult(AuthenticationState authenticationState, String message) {
        this.message = message;
        this.authenticationState = authenticationState;
    }

    /**
     * Constructor of a successful authentication result.
     *
     * @param authenticationState the state of the authentication
     * @param token               the token which should be transferred to the user.
     */
    public AuthenticationResult(AuthenticationState authenticationState, Token token) {
        this.authenticationState = authenticationState;
        this.token = token;
    }

    /**
     * Gets authentication state.
     *
     * @return the authentication state
     */
    public AuthenticationState getAuthenticationState() {
        return authenticationState;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public Token getToken() {
        return token;
    }
}
