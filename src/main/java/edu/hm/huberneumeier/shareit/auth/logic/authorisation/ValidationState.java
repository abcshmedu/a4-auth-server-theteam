package edu.hm.huberneumeier.shareit.auth.logic.authorisation;

/**
 * States which could be holden by ValidationResults.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public enum ValidationState {
    /**
     * Success validation state.
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * Wrong input validation state.
     */
    WRONG_INPUT(301, "WRONG_INPUT"),
    /**
     * Token expired validation state.
     */
    TOKEN_EXPIRED(301, "TOKEN_EXPIRED"),
    /**
     * Token invalid validation state.
     */
    TOKEN_INVALID(301, "TOKEN_INVALID"),
    /**
     * No permisson validation state.
     */
    NO_PERMISSON(301, "NO_PERMISSION");

    private final int code;
    private final String name;

    /**
     * Constructor of validation state.
     *
     * @param code the code of the validation state.
     * @param name the name of the validation state.
     */
    ValidationState(int code, String name) {
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
