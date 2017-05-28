package edu.hm.huberneumeier.shareit.auth.logic.authentication;

/**
 * States which can be hold by an AuthenticationResult.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 25.05.2017
 */
public enum AuthenticationState {
    /**
     * Success authentication state.
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * Wrong input authentication state.
     */
    WRONG_INPUT(301, "WRONG_INPUT");

    private final int code;
    private final String name;

    /**
     * Constructor of an authentication state.
     *
     * @param code the code of the authentication state.
     * @param name the name of the authentication state.
     */
    AuthenticationState(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
