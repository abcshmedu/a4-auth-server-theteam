package edu.hm.huberneumeier.shareit.auth.logic.authorisation;

import edu.hm.huberneumeier.shareit.auth.media.Authorisation;

/**
 * Interface for the internal authorisation service.
 *
 * @author Tobias Huber
 * @version 28.05.2017
 */
public interface AuthServiceInternal {
    /**
     * Method to validate a token and a authorisation.
     * So, is a special user (which is specified by the token) allowed to do something.
     *
     * @param token         the token for which to authorisation should be checked.
     * @param authorisation the authorisation which is necessary.
     * @return the result of the validation.
     */
    public ValidationResult validate(String token, Authorisation authorisation);
}
