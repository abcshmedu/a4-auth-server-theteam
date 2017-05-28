package edu.hm.huberneumeier.shareit.auth.logic.authorisation;

import edu.hm.huberneumeier.shareit.auth.logic.data.UserData;
import edu.hm.huberneumeier.shareit.auth.media.Authorisation;
import edu.hm.huberneumeier.shareit.auth.media.Token;
import edu.hm.huberneumeier.shareit.auth.media.User;

import java.util.List;

/**
 * Implementation of the internal authorisation api.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class AuthorisationImpl implements AuthServiceInternal {

    @Override
    public ValidationResult validate(String tokenKey, Authorisation authorisation) {
        Token token = Token.getTokenToKey(tokenKey);
        ValidationResult result = validateToken(token);
        if (result.getValidationState().equals(ValidationState.SUCCESS)) {
            result = validateAuthorisation(UserData.getUser(token), authorisation);
        }
        return result;
    }

    /**
     * Check if a token is valid.
     *
     * @param token the token which should be checked.
     * @return the result of the validation.
     */
    private ValidationResult validateToken(Token token) {
        ValidationResult result;
        if (token != null && UserData.userExists(token)) {
            if (token.getValidUntil() > System.currentTimeMillis()) {
                result = new ValidationResult(ValidationState.SUCCESS, "");
            } else {
                result = new ValidationResult(ValidationState.TOKEN_EXPIRED, "Unauthorised - Your token is expired.");
                UserData.removeUserToken(token);
            }
        } else {
            result = new ValidationResult(ValidationState.TOKEN_INVALID, "Unauthorised - Your token is invalid.");
        }
        return result;
    }

    /**
     * Check if the user associated with the token, has the authorisation which is necessary.
     *
     * @param user          the user for which the authorisation should be validated.
     * @param authorisation the authorisation which should be validated.
     * @return the result of the validation.
     */
    private ValidationResult validateAuthorisation(User user, Authorisation authorisation) {
        ValidationResult result;
        List<Authorisation> auths = user.getAuthorisationGroup().getAuthorisations();
        if (auths.contains(authorisation)) {
            result = new ValidationResult(ValidationState.SUCCESS, "");
        } else {
            result = new ValidationResult(ValidationState.NO_PERMISSON, "Unauthorised - You are not allowed to do this.");
        }
        return result;
    }
}
