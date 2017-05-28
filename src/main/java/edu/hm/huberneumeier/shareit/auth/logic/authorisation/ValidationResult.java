package edu.hm.huberneumeier.shareit.auth.logic.authorisation;

/**
 * Results given back from validations.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class ValidationResult {
    private ValidationState validationState;
    private String message;

    /**
     * Instantiates a new Validation result.
     */
    public ValidationResult() {
    }

    /**
     * Instantiates a new Validation result.
     *
     * @param validationState the validation state
     * @param message         the message
     */
    public ValidationResult(ValidationState validationState, String message) {
        this.message = message;
        this.validationState = validationState;
    }

    /**
     * Gets validation state.
     *
     * @return the validation state
     */
    public ValidationState getValidationState() {
        return validationState;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
