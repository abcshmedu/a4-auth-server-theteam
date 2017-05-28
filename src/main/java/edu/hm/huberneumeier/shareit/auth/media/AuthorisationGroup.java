package edu.hm.huberneumeier.shareit.auth.media;

import java.util.ArrayList;
import java.util.List;

/**
 * Combine multiple Authorisations to AuthorisationGroups.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 28.05.2017
 */
public enum AuthorisationGroup {

    /**
     * Admins authorisation group.
     */
    ADMINS("admins", getAdminAuthorisations()),
    /**
     * Users authorisation group.
     */
    USERS("users", getUserAuthorisations());

    private final String name;
    private final List<Authorisation> authorisations;

    /**
     * Constructor of authorisation group.
     *
     * @param name           the name of the authorisation group.
     * @param authorisations the authorisations mapped to the group.
     */
    AuthorisationGroup(String name, List<Authorisation> authorisations) {
        this.name = name;
        this.authorisations = authorisations;
    }

    /**
     * The authorisations mapped to the admin group.
     *
     * @return
     */
    private static List<Authorisation> getAdminAuthorisations() {
        final List<Authorisation> adminAuths = new ArrayList<>();
        adminAuths.add(Authorisation.BOOK_CREATE);
        adminAuths.add(Authorisation.BOOK_READ);
        adminAuths.add(Authorisation.BOOK_UPDATE);
        adminAuths.add(Authorisation.BOOK_DELETE);
        adminAuths.add(Authorisation.DISC_CREATE);
        adminAuths.add(Authorisation.DISC_READ);
        adminAuths.add(Authorisation.DISC_UPDATE);
        adminAuths.add(Authorisation.DISC_DELETE);
        return adminAuths;
    }

    /**
     * The authorisation mapped to the user group.
     *
     * @return
     */
    private static List<Authorisation> getUserAuthorisations() {
        final List<Authorisation> userAuths = new ArrayList<>();
        userAuths.add(Authorisation.BOOK_READ);
        userAuths.add(Authorisation.DISC_READ);
        return userAuths;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets authorisations.
     *
     * @return the authorisations
     */
    public List<Authorisation> getAuthorisations() {
        return authorisations;
    }
}
