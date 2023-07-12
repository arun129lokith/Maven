package com.instagram.view;

import com.instagram.controller.UserController;
import com.instagram.model.UserBuilder;
import com.instagram.model.Post;
import com.instagram.model.User;

/**
 * <p>
 * Users to interact with application for accessing the features of the application.
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserView extends View {

    private final UserController USER_CONTROLLER = UserController.getInstance();
    private  final AuthenticationView AUTHENTICATION_VIEW = AuthenticationView.getInstance();
    private static UserView userView = null;

    private UserView(){}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user view object.
     */
    public static UserView getInstance() {
        return null == userView ?  userView = new UserView() : userView;
    }

    /**
     * <p>
     * Gets the valid username from the user.
     * </p>
     *
     * @return The valid username of the user.
     */
    public String getName() {
        System.out.println(String.join("\n", "Enter Your UserName:",
                "[Username Contains Lowercase Letter And Underscore And Digits Without Space]",
                "If You Want To Exit Press '!'"));
        final String name = SCANNER.nextLine().trim();

        exitMenu(name);

        return VALIDATION.validateUserName(name) ? name : getName();
    }

    /**
     * <p>
     * Gets the valid username is not already exists in the application.
     * </p>
     *
     * @param name Represents user name.
     * @return The valid username.
     */
    public String getValidName(final String name) {
        if (USER_CONTROLLER.isNameExist(name)) {
            System.out.println("User Name Already Exist. Please Re-enter The Valid User Name");

            return getValidName(getName());
        }

        return name;
    }

    /**
     * <p>
     * Gets the valid email from the user.
     * </p>
     *
     * @return The valid email of the user.
     */
    public String getEmail() {
        System.out.println(String.join(" ", "Enter Your EmailId:",
                "\n[EmailId Must Contains Lowercase Letter[a-z] Then Contain Digits[0-9] Is Optional One",
                "'@' After Must Contains [5 Or Above] Lowercase Letter And '.' After Must Contains 2 Or 3 ",
                "Characters]\nIF You Want To Exit Press '!'"));
        final String email = SCANNER.nextLine().trim();

        exitMenu(email);

        return VALIDATION.validateEmail(email) ? email : getEmail();
    }

    /**
     * <p>
     * Gets the email is not already exists in the application.
     * </p>
     *
     * @param email Represents user email.
     * @return The mobile number of the user.
     */
    public String getValidEmail(final String email) {
        if (USER_CONTROLLER.isEmailExist(email)) {
            System.out.println("User Email Is Already Exist. Please Re-enter The Valid User Name");

            return getValidEmail(getEmail());
        }

        return email;
    }

    /**
     * <p>
     * Gets the valid password from the user.
     * </p>
     *
     * @return The valid password of the user.
     */
    public String getPassword() {
        System.out.println(String.join(" ", "Enter Your Password:", "\n[Password Must Contain At least",
                "One Uppercase, One Lowercase, Special Character And Digits In The Range 8-20 Characters]",
                "\nIF You Want To Exit Press '!'"));
        final String password = SCANNER.nextLine().trim();

        exitMenu(password);

        return VALIDATION.validatePassword(password) ? password : getPassword();
    }

    /**
     * <p>
     * Gets the valid mobile number from the user.
     * </p>
     *
     * @return The mobile number of the user.
     */
    public String getMobileNumber() {
        System.out.println(String.join(" ", "Enter Your Mobile Number:", "\n[Mobile Number Must",
                "Contains 10 Digits  And Starts With [6-9]]", "\nIf You Want To Exit Press '!'"));
        final String mobileNumber = SCANNER.nextLine().trim();

        exitMenu(mobileNumber);

        return VALIDATION.validateMobileNumber(mobileNumber) ? mobileNumber : getMobileNumber();
    }

    /**
     * <p>
     * Gets the mobile number is not already exists in the application.
     * </p>
     *
     * @param mobileNumber Represents user mobile number.
     * @return The mobile number of the user.
     */
    public String getValidMobileNumber(final String mobileNumber) {
        if (USER_CONTROLLER.isMobileNumberExist(mobileNumber)) {
            System.out.println("User Mobile Number Is Already Exist. Please Re-enter The Valid User Name");

            return getValidMobileNumber(getMobileNumber());
        }

        return mobileNumber;
    }

    /**
     * <p>
     * Gets the user details of the user.
     * </p>
     */
    private void getUser() {
        System.out.println("Enter Your UserId:");
        final User user = USER_CONTROLLER.getUser(getUserId());

        System.out.println(null != user ? user : "User Not Found");
    }

    /**
     * <p>
     * Gets the all user details.
     * </p>
     */
    private void getAllUsers() {
        System.out.println(USER_CONTROLLER.getAllUsers());
    }

    /**
     * <p>
     * Prints the features of the application.
     * </p>
     *
     * @param id Represents user id.
     */
    public void userScreen(final Long id) {
        System.out.println(String.join(" ","Click 1 To User Post Menu\nClick 2 To Logout", "\nClick 3",
                "To Get User\nClick 4 To Get All User \nClick 5 To Update User\nClick 6 To Delete User",
                "\nClick 7 To Main Menu\nClick 8 To Display Post Of The User"));
        final PostView postView = PostView.getInstance();

        switch (getChoice()) {
            case 1:
                postView.menu(id);
                break;
            case 2:
                logout();
                break;
            case 3:
                getUser();
                break;
            case 4:
                getAllUsers();
                break;
            case 5:
                update(id);
                break;
            case 6:
                delete();
                break;
            case 7:
                AUTHENTICATION_VIEW.menu();
                break;
            case 8:
                displayPost();
                break;
            default:
                System.out.println("Invalid User Choice. Please Try Again\n[Enter The Choice In The Range 1-8]");
                userScreen(id);
        }
        userScreen(id);
    }

    /**
     * <p>
     * Displays the collection of user post.
     * </p>
     */
    private void displayPost() {
        System.out.println("Enter The User Id To Get Collection Of Post:");
        final User user = getUserById(getUserId());

        if (null != user) {

            if (! user.getPosts().isEmpty()) {

                for (final Post post : user.getPosts()) {

                    if (post.getUserId().equals(user.getId())) {
                        System.out.println(post);
                    }
                }
            } else {
                System.out.println("Post Not Created By The User");
            }
        } else {
            System.out.println("User Not Found.Please Try Again");
        }
    }

    /**
     * <p>
     * Gets the valid user id.
     * </p>
     *
     * @return The user id.
     */
    private Long getUserId() {
        try {
            return Long.parseLong(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid User Id Format. Please Enter A Number");
        }

        return getUserId();
    }

    /**
     * <p>
     * Users to enter update details of the user information.
     * </p>
     *
     * @param id Represents user id.
     */
    private void update(final Long id) {
        final UserBuilder user = UserBuilder.getInstance();
        final User existingUser = getUserById(id);

        System.out.println(existingUser);
        user.withId(id);
        user.withName(exitAccess() ? existingUser.getName() : getValidName(getName()));
        user.withPassword(exitAccess() ? existingUser.getPassword() : getPassword());
        user.withEmail(exitAccess() ? existingUser.getEmail() : getValidEmail(getEmail()));
        user.withMobileNumber(exitAccess() ? existingUser.getMobileNumber() : getValidMobileNumber(getMobileNumber()));

        System.out.println(USER_CONTROLLER.update(user.build()) ? "Account Updated Successfully" : "User Not Found");
    }

    /**
     * <p>
     * Gets user information by id of the user.
     * </p>
     *
     * @param id Represents user id.
     * @return Represents {@link User} information.
     */
    public User getUserById(final Long id) {
        return USER_CONTROLLER.getUser(id);
    }

    /**
     * <p>
     * Users to delete his account.
     * </p>
     */
    private void delete() {
        System.out.println("Enter Your User Id:");

        if (USER_CONTROLLER.delete(getUserId())) {
            System.out.println("User Account Deleted Successfully");
            AUTHENTICATION_VIEW.menu();
        } else {
            System.out.println("User Not Found. Please Try Again");
        }
    }

    /**
     * <p>
     * Users to log out the page.
     * </p>
     */
    private void logout() {
        System.out.println("Logged Out Successfully");

        if (exitAccess()) {
            SCANNER.close();
            System.exit(0);
        }
        AUTHENTICATION_VIEW.menu();
    }

    /**
     * <p>
     * Exits the screen to menu.
     * </p>
     *
     * @param userChoice Represents the choice of the user.
     */
    private void exitMenu(final String userChoice) {
        if (VALIDATION.backMenu(userChoice)) {
            AUTHENTICATION_VIEW.menu();
        }
    }
}

