package edu.hm.huberneumeier.shareit.auth.logic.data;

import edu.hm.huberneumeier.shareit.auth.media.Token;
import edu.hm.huberneumeier.shareit.auth.media.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapping between users and there names & tokens.
 *
 * @author Andreas Neumeier
 * @author Tobias Huber
 * @version 28.05.2017
 */
public class UserData {
    private static Map<String, User> userList = User.getUserList();
    private static Map<Token, User> tokenUserMap = new HashMap<>();

    /**
     * Gets a user out of the user list.
     *
     * @param name the name
     * @return the user
     */
    public static User getUser(String name) {
        return userList.get(name);
    }

    /**
     * Gets user based on a token.
     *
     * @param token the token
     * @return the user
     */
    public static User getUser(Token token) {
        return tokenUserMap.get(token);
    }

    /**
     * Checks if a user exists.
     *
     * @param name the name
     * @return the boolean
     */
    public static Boolean userExists(String name) {
        return userList.containsKey(name);
    }

    /**
     * Checks if a user exists to a token.
     *
     * @param token the token
     * @return the boolean
     */
    public static Boolean userExists(Token token) {
        return tokenUserMap.containsKey(token);
    }

    /**
     * Remove user token user.
     *
     * @param token the token
     * @return the user
     */
    public static User removeUserToken(Token token) {
        return userList.get(token);
    }

    /**
     * Sets user token.
     *
     * @param user  the user
     * @param token the token
     */
    public static void setUserToken(User user, Token token) {
        user.setToken(token);
        tokenUserMap.put(token, user);
    }

    /**
     * Sets user token.
     *
     * @param name  the name
     * @param token the token
     */
    public static void setUserToken(String name, Token token) {
        setUserToken(getUser(name), token);
    }
}
