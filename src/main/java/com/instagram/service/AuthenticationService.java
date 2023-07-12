package com.instagram.service;

import com.instagram.model.User;

public interface AuthenticationService {

    /**
     * <p>
     * Signs up a new user with user details of user class.
     * </p>
     *
     * @param user Represents {@link User} details.
     * @return True if sign-up is successful, false otherwise.
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Signs in a new user with user details of user class.
     * </p>
     *
     * @param user Represents {@link User} details.
     * @return True if sign-in is successful, false otherwise.
     */
    boolean signIn(final User user);
}
