package edu.hm.huberneumeier.shareit.auth.media.jsonMappings;

/**
 * Used for json requests to the authorisation service.
 *
 * @author Andreas Neumeier
 * @version 28.05.2017
 */
public class AuthorisationIDRequest {
    private Integer authorisationID;

    /**
     * Instantiates a new Authorisation id request.
     */
    public AuthorisationIDRequest() {

    }

    /**
     * Instantiates a new Authorisation id request.
     *
     * @param authorisationID the authorisation id
     */
    public AuthorisationIDRequest(Integer authorisationID) {
        this.authorisationID = authorisationID;
    }

    /**
     * Gets authorisation id.
     *
     * @return the authorisation id
     */
    public Integer getAuthorisationID() {
        return authorisationID;
    }
}
