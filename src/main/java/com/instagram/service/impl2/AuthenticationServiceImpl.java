package com.instagram.service.impl2;

import com.instagram.dao.AuthenticationDao;
import com.instagram.dao.impl.AuthenticationDaoImpl;
import com.instagram.model.User;
import com.instagram.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static AuthenticationServiceImpl authenticationServiceImpl;
    private static final AuthenticationDao AUTHENTICATION_DAO = AuthenticationDaoImpl.getInstance();

    private AuthenticationServiceImpl() {}

    public static AuthenticationServiceImpl getInstance() {
        return null == authenticationServiceImpl ? authenticationServiceImpl = new AuthenticationServiceImpl()
                : authenticationServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details.
     * @return True if sign-up is successful, false otherwise.
     */
    @Override
    public boolean signUp(final User user) {
        return AUTHENTICATION_DAO.signUp(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details.
     * @return True if sign-in is successful, false otherwise.
     */
    @Override
    public boolean signIn(final User user) {
        return null != user.getMobileNumber() ? isMobileNumberExist(user) : isEmailExist(user);
    }

    /**
     * Checks the mobile number and password is exists.
     *
     * @param user Represents {@link User} details.
     * @return True if mobile number is exists, false otherwise.
     */
    private boolean isMobileNumberExist(final User user) {
        return AUTHENTICATION_DAO.isMobileNumberExist(user);
    }

    /**
     * Checks the email and password is exists.
     *
     * @param user Represents {@link User} details.
     * @return True if email is exists, false otherwise.
     */
    private boolean isEmailExist(final User user) {
        return AUTHENTICATION_DAO.isEmailExist(user);
    }
}
