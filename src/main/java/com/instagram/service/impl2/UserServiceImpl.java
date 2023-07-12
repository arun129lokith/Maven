package com.instagram.service.impl2;

import com.instagram.service.UserService;
import com.instagram.dao.UserDao;
import com.instagram.model.User;
import com.instagram.dao.impl.UserDaoImpl;

import java.util.Collection;

/**
 * <p>
 * Implements the service of the user related operation.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl = null;
    private static final UserDao USER_DAO = UserDaoImpl.getInstance();

    private UserServiceImpl() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user service implementation object.
     */
    public static UserServiceImpl getInstance() {
        return null == userServiceImpl ? userServiceImpl = new UserServiceImpl() : userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id.
     * @return The user details.
     */
    @Override
    public User getUser(final Long id) {
        return USER_DAO.getUser(id);
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of user details.
     */
    @Override
    public Collection<User> getAllUsers() {
        return USER_DAO.getAllUsers();
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details.
     * @return True if update is successful, false otherwise.
     */
    @Override
    public boolean update(final User user) {
        return USER_DAO.update(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id.
     * @return True if account is deleted, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        return USER_DAO.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} detail.
     * @return Users id.
     */
    @Override
    public Long getId(final User user) {
        return USER_DAO.getId(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param name Represents user name.
     * @return True if name is exists, false otherwise.
     */
    @Override
    public boolean isNameExist(final String name) {
        return USER_DAO.isNameExist(name);
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email.
     * @return True if email is exists, false otherwise.
     */
    @Override
    public boolean isEmailExist(final String email) {
        return USER_DAO.isEmailExist(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param mobileNumber Represents user mobile number.
     * @return True if mobile number is exists, false otherwise.
     */
    @Override
    public boolean isMobileNumberExist(final String mobileNumber) {
        return USER_DAO.isMobileNumberExist(mobileNumber);
    }
}
