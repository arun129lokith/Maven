package com.instagram.view;

import com.instagram.controller.LikeController;
import com.instagram.model.Like;
import com.instagram.model.User;

import java.util.Collection;

/**
 * <p>
 * Provides user reaction for the post of the application.
 * </p>
 *
 * @author Arun.
 * @version 1.0.
 */
public class LikeView extends View {

    private final PostView POST_VIEW = PostView.getInstance();
    private final UserView USER_VIEW = UserView.getInstance();
    private static LikeView likeView = null;
    private final LikeController LIKE_CONTROLLER = LikeController.getInstance();
    private static Long id = 1L;

    private LikeView() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The like view object.
     */
    public static LikeView getInstance() {
        return null == likeView ? likeView = new LikeView() : likeView;
    }

    /**
     * <p>
     * Displays the user reaction for the post of the application.
     * </p>
     *
     * @param userId Represents id of the user.
     */
    public void menu(final Long userId) {
        System.out.println(String.join("","Click 1 To Like Post\nClick 2 To Unlike Post\nClick 3 To",
                "Get All User Liked The Post\nClick 4 To Get Like Count Of The Post\nClick 5 To Post Menu\nClick 6 TO",
                "User Menu"));

        if (exitAccess()) {
            POST_VIEW.menu(userId);
        }

        switch (getChoice()) {
            case 1:
                likePost(userId);
                break;
            case 2:
                unlikePost();
                break;
            case 3:
                getLikedUser();
                break;
            case 4:
                getLikeCount();
                break;
            case 5:
                POST_VIEW.menu(userId);
                break;
            case 6:
                USER_VIEW.userScreen(userId);
                break;
            default:
                System.out.println("Invalid Choice. Please Enter A Choice In Range[1-5]");
                break;
        }
        menu(userId);
    }

    /**
     * <p>
     * Creates the like for the post.
     * </p>
     *
     * @param userId Represents id of the user.
     */
    private void likePost(final Long userId) {
        final Like like = new Like();

        like.setId(idGenerator());
        like.setUserId(userId);
        like.setPostId(getPostId());

        LIKE_CONTROLLER.likePost(like);

        System.out.println("Post Liked Successfully");
    }

    /**
     * <p>
     * Removes the like for the post provided by the user.
     * </p>
     */
    private void unlikePost() {
        final Long id = getLikeId();

        System.out.println(LIKE_CONTROLLER.unlikePost(id) ? "Like Removed Successfully" : "Like Not Found");
    }

    /**
     * <p>
     * Gets the collection of user who react for the post.
     * </p>
     */
    private void getLikedUser() {
        final Collection<User> users = LIKE_CONTROLLER.getLikeUser(getPostId());

        if (null != users && !users.isEmpty()) {

            for (final User user : users) {
                System.out.println(user.getName());
            }
        } else {
            System.out.println("Post Not Liked By Any User");
        }
    }

    /**
     * <p>
     * Gets the count of the like for the post.
     * </p>
     */
    private void getLikeCount() {
        final Long getCount = LIKE_CONTROLLER.getLikeCount(getPostId());

        System.out.println(null != getCount ? getCount : "Post Not Liked By Any User");
    }

    /**
     * <p>
     * Gets the like id for the post.
     * </p>
     *
     * @return The id of the like.
     */
    private Long getLikeId() {
        System.out.println("Enter Your Like Id:");

        try {
            return Long.valueOf(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid Like Id Format. Please Enter A Number");
        }

        return getLikeId();
    }

    /**
     * <p>
     * Gets the id of the post.
     * </p>
     *
     * @return The post id.
     */
    private Long getPostId() {
        final Long id = POST_VIEW.getPostId();

        if (null == POST_CONTROLLER.getPost(id)) {
            System.out.println("Post Not Found By The Id. Please Try Again");

            return getPostId();
        }

        return id;
    }

    /**
     * <p>
     * Generates id for the new like.
     * </p>
     *
     * @return The like id.
     */
    private Long idGenerator() {
        return id++;
    }
}
