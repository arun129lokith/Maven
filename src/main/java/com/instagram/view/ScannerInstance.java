package com.instagram.view;

import java.util.Scanner;

/**
 * <p>
 * Creates an instance of scanner in single time only.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class ScannerInstance {

    private static Scanner scanner;

    /**
     * Gets a static instance of the scanner.
     *
     * @return The scanner object.
     */
    public static synchronized Scanner getInstance() {
        return null == scanner ? scanner = new Scanner(System.in) : scanner;
    }
}
