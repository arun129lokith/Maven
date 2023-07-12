package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.impl2.UserServiceImpl;
import com.instagram.service.UserService;


import java.util.Collection;

/**
 * <p>
 * Handles the user related operation and responsible for receiving user input and processing it.
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserController {

    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    private static UserController userController = null;

    private UserController() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user controller object.
     */
    public static UserController getInstance() {
       return null == userController ? userController = new UserController() : userController;
    }

    /**
     * <p>
     * Gets the user details of the user.
     * </p>
     *
     * @param id Represents user id.
     * @return The user details.
     */
    public User getUser(final Long id) {
        return USER_SERVICE.getUser(id);
    }

    /**
     * <p>
     * Collects the all user information.
     * </p>
     *
     * @return The collection of user details.
     */
    public Collection<User> getAllUsers() {
        return USER_SERVICE.getAllUsers();
    }

    /**
     * <p>
     * Updates the user details.
     * </p>
     *
     * @param user Represent {@link User} details.
     * @return True if user details is update, false otherwise
     */
    public boolean update(final User user) {
        return USER_SERVICE.update(user);
    }

    /**
     * <p>
     * Deletes the user account details.
     * </p>
     *
     * @param id Represents user id.
     * @return True if account is deleted, false otherwise.
     */
    public boolean delete(final Long id) {
        return USER_SERVICE.delete(id);
    }

    /**
     * <p>
     * Gets the user id of the user.
     * </p>
     *
     * @param user Represents {@link User} details.
     * @return The user id of the user.
     */
    public Long getId(final User user) {
        return USER_SERVICE.getId(user);
    }

    /**
     * <p>
     * Checks the username is exists.
     * </p>
     *
     * @param name Represents user name.
     * @return True if username is exists, false otherwise.
     */
    public boolean isNameExist(final String name) {
        return USER_SERVICE.isNameExist(name);
    }

    /**
     * <p>
     * Checks the email is exists.
     * </p>
     *
     * @param email Represents user email.
     * @return True if email is exists, false otherwise.
     */
    public boolean isEmailExist(final String email) {
        return USER_SERVICE.isEmailExist(email);
    }

    /**
     * <p>
     * Checks the mobile number is exists.
     * </p>
     *
     * @param mobileNumber Represents user mobile number.
     * @return True if mobile number is exists, false otherwise.
     */
    public boolean isMobileNumberExist(final String mobileNumber) {
        return USER_SERVICE.isMobileNumberExist(mobileNumber);
    }
}