package com.instagram.view;

import com.instagram.model.Post;
import com.instagram.model.Post.Format;
import com.instagram.model.PostBuilder;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * <p>
 * Displays post information of the user and provides methods to render post data on the screen.
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class PostView extends View {

    private final UserView USER_VIEW = UserView.getInstance();
    private static PostView postView = null;

    private PostView() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The post view object.
     */
    public static PostView getInstance() {
        return null == postView ? postView = new PostView() : postView;
    }

    /**
     * <p>
     * Prints the post menu of the user.
     * </p>
     *
     * @param userId Represents user id.
     */
    public void menu(final Long userId) {
        System.out.println(String.join(" ","Click 1 To Create Post\nClick 2 To Display All Post",
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Post By Id\nClick 6 To User",
                "Screen\nClick 7 To Like Menu"));

        if (exitAccess()) {
            USER_VIEW.userScreen(userId);
        }
        final LikeView like = LikeView.getInstance();

        switch (getChoice()) {
            case 1:
                create(userId);
                break;
            case 2:
                displayAll();
                break;
            case 3:
                delete();
                break;
            case 4:
                update(userId);
                break;
            case 5:
                getPost();
                break;
            case 6:
                USER_VIEW.userScreen(userId);
                break;
            case 7:
                like.menu(userId);
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter The Choice In The Range[1-7]");
                menu(userId);
                break;
        }
        menu(userId);
    }

    /**
     * <p>
     * Gets user location.
     * </p>
     *
     * @return The location of the user.
     */
    private String getLocation() {
        System.out.println("Enter Your Location Of Your Post:");
        final String location = SCANNER.nextLine().trim();

        return VALIDATION.isValidLocation(location) ? location : getLocation();
    }

    /**
     * <p>
     * Gets user caption.
     * </p>
     *
     * @return The caption of the user.
     */
    private String getCaption() {
        System.out.println("Enter Your Caption:");

        return SCANNER.nextLine();
    }

    /**
     * <p>
     * Creates the post of the user.
     * </p>
     *
     * @param userId Represents user id.
     */
    private void create(final Long userId) {
        final PostBuilder post = PostBuilder.getInstance();

        post.withId(idGenerator());
        post.withFormat(getFormat());
        post.withLocation(getLocation());
        post.withCaption(getCaption());
        post.withUploadedTime(Timestamp.from(Instant.now()));
        post.withUserId(userId);


        System.out.println(POST_CONTROLLER.create(post.build()) ? "User Posted Successfully" : "Post Not Created");
        menu(userId);
    }

    /**
     * <p>
     * Gets the format of the post of the user.
     * </p>
     *
     * @return Represents {@link Post} format of the user.
     */
    private Format getFormat() {
        System.out.println("Click 1 To Image Format\nClick 2 To Video Format");
        final Format format = Format.existingFormat(USER_VIEW.getChoice());

        if (null != format) {
            return format;
        } else {
            System.out.println("Invalid Post Format Choice. Please Enter The Choice For Post Format In The Range[1-2]");

            return getFormat();
        }
    }

    /**
     * <p>
     * Prints the all posts, posted by user.
     * </p>
     */
    private void displayAll() {
        System.out.println(POST_CONTROLLER.getAllPost());
    }

    /**
     * <p>
     * Gets the post detail of the user.
     * </p>
     *
     * @return The post details of the id.
     */
    private Post getPost() {
        final Post post = POST_CONTROLLER.getPost(getPostId());

        System.out.println(null != post ? post : "Post Not Found");

        return post;
    }

    /**
     * <p>
     * Users to delete the post.
     * </p>
     */
    private void delete() {
        System.out.println("Enter Your PostId:");
        System.out.println(POST_CONTROLLER.delete(getPostId()) ? "Post Deleted Successfully"
                : "Post Not Found");
    }

    /**
     * <p>
     * Sets the details of the user post to update.
     * </p>
     */
    private void update(final Long userId) {
        System.out.println("Get The Post Of The User To Update Post Details");
        final Post post = new Post();
        final Post existingPost = getPostById(userId);

        if (null != existingPost) {
            post.setId(existingPost.getId());
            post.setUserId(existingPost.getUserId());
            post.setFormat(existingPost.getFormat());
            post.setLocation(USER_VIEW.exitAccess() ? existingPost.getLocation() : getLocation());
            post.setCaption(USER_VIEW.exitAccess() ? existingPost.getCaption() : getCaption());
            post.setUploadedTime(Timestamp.from(Instant.now()));

            POST_CONTROLLER.update(post);
            System.out.println("Post Updated Successfully");
        } else {
            System.out.println("Please Try Again");
        }
    }

    /**
     * <p>
     * Gets valid post id of the user.
     * </p>
     *
     * @return The post id of the user.
     */
    public Long getPostId() {
        System.out.println("Enter Your PostId:");

        try {
            return Long.parseLong(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid Post Id Format. Please Enter A Number");
        }

        return getPostId();
    }

    /**
     * <p>
     * Gets a post of the user, if the post is posted by the user.
     * </p>
     *
     * @param userId Represents id of the user.
     * @return The post details of the user.
     */
    private Post getPostById(final Long userId) {
        final Post post = getPost();

        if (null != post) {
            final Post existingPost = POST_CONTROLLER.getPost(post.getId(), userId);

            System.out.println(null != existingPost ? existingPost : "Post Not Created By This User And No Access To Update");

            return existingPost;
        }

        return null;
    }

    /**
     * <p>
     * Generates id for the new post.
     * </p>
     *
     * @return The post id.
     */
    private long idGenerator() {
        return POST_CONTROLLER.getAllPost().size() + 1;
    }
}



