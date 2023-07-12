package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.AuthenticationService;
import com.instagram.service.impl2.AuthenticationServiceImpl;

public class AuthenticationController {

    private static final AuthenticationService AUTHENTICATION_SERVICE = AuthenticationServiceImpl.getInstance();
    private static AuthenticationController authenticationController;

    private AuthenticationController() {}

    /**
     * <p>
     * Gets the object of the authentication controller class.
     * </p>
     *
     * @return The authentication controller object.
     */
    public static AuthenticationController getInstance() {
        return null == authenticationController ? authenticationController = new AuthenticationController()
                : authenticationController;
    }

    /**
     * <p>
     * Signs up a new user with user details of user class.
     * </p>
     *
     * @param user Represents {@link User} details.
     * @return True if sign-up is successful, false otherwise.
     */
    public boolean signUp(final User user) {
        return AUTHENTICATION_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Signs in a new user with user details of user class.
     * </p>
     *
     * @param user Represents {@link User} details.
     * @return True if sign-in is successful, false otherwise.
     */
    public boolean signIn(final User user) {
        return AUTHENTICATION_SERVICE.signIn(user);
    }
}
