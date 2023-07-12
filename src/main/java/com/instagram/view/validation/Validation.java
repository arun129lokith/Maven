package com.instagram.view.validation;

/**
 * <P>
 * Handles the validation of the user details.
 * </P>
 *
 * @author Arun
 * @version 1.0
 */
public class Validation {

    private static Validation validation = null;

    private Validation() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The validation object.
     */
    public static Validation getInstance() {
       return null == validation ? validation = new Validation() : validation;
    }

    /**
     * <p>
     * Validates the username of the user.
     * </p>
     *
     * @param userName The username of the user.
     * @return True if username is valid, false otherwise.
     */
    public boolean validateUserName(final String userName) {
        return userName.matches("^(?!.*[._]{2})[A-Za-z][A-Za-z\\d_.]{0,28}[A-Za-z\\d]$");
    }

    /**
     * <p>
     * Validates the email of the user.
     * </p>
     *
     * @param email The email of the user.
     * @return True if email is valid, false otherwise.
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[a-z][a-z\\d._]+@[a-z]{5,}.[a-z]{2,3}$");
    }

    /**
     * <p>
     * Validates the password of the user.
     * </p>
     *
     * @param password The password of the user.
     * @return True if password is valid, false otherwise.
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * <p>
     * Validates the mobile number of the user.
     * </p>
     *
     * @param mobileNumber The mobile number of yhe user.
     * @return True if mobile number is valid, false otherwise.
     */
    public boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("^[6-9]\\d{9}$");
    }

    /**
     * <p>
     * Goes to main menu of the application.
     * </p>
     *
     * @param userInput The input of the user details.
     */
    public boolean backMenu(final String userInput) {
        return userInput.contains("!");
    }

    /**
     * <p>
     * Validates the user for exit.
     * </p>
     *
     * @param exitChoice The exit choice of the user.
     * @return True if exit choice condition is satisfied, false otherwise.
     */
    public boolean isExit(final String exitChoice) {
        return "No".equalsIgnoreCase(exitChoice) || "N".equalsIgnoreCase(exitChoice);
    }

    /**
     * <p>
     * Validates the location of the post.
     * </p>
     *
     * @param location Represents the location of the user.
     * @return True if location is valid, false otherwise.
     */
    public boolean isValidLocation(final String location) {
        return location.matches("^[a-zA-z]+$");
    }
}


