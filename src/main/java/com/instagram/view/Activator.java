package com.instagram.view;

/**
 * <p>
 * Activates the application.
 * </p>
 *
 * @author Arun.
 * @version 1.1.
 */
public class Activator {

    public static void main(String[] args) {
        final AuthenticationView authenticationView = AuthenticationView.getInstance();

        authenticationView.menu();
    }
}
