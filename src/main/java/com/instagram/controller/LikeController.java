package com.instagram.controller;

import com.instagram.service.impl2.LikeServiceImpl;
import com.instagram.model.Like;
import com.instagram.model.User;
import com.instagram.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Handles the like related operation of the post and responsible for receiving user input and processing it.
 * </p>
 *
 * @author Arun.
 * @version 1.0.
 */
public class LikeController {

    private static LikeController likeController;
    private static final LikeService LIKE_SERVICE = LikeServiceImpl.getInstance();

    private LikeController() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The like controller object.
     */
    public static LikeController getInstance() {
        return null == likeController ? likeController = new LikeController() : likeController;
    }

    /**
     * <p>
     * Creates the like for the post.
     * </p>
     *
     * @param like Represents {@link Like} details.
     */
    public void likePost(final Like like) {
        LIKE_SERVICE.likePost(like);
    }

    /**
     * <p>
     * Removes the like for the post provided by the user.
     * </p>
     *
     * @param id Represents like id.
     * @return True if like is removed, false otherwise.
     */
    public boolean unlikePost(final Long id) {
        return LIKE_SERVICE.unlikePost(id);
    }

    /**
     * <p>
     * Gets the collection of user who react for the post.
     * </p>
     *
     * @param postId Represents post id.
     * @return The collection of user.
     */
    public Collection<User> getLikeUser(final Long postId) {
        return LIKE_SERVICE.getLikeUser(postId);
    }

    /**
     * <p>
     * Gets the count of the like for the post.
     * </p>
     *
     * @param postId Represents post id.
     * @return The count of the like.
     */
    public Long getLikeCount(final Long postId) {
        return LIKE_SERVICE.getLikeCount(postId);
    }
}
