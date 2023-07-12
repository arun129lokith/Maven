package com.instagram.controller;

import com.instagram.model.Post;
import com.instagram.service.PostService;
import com.instagram.service.impl2.PostServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Handles the post related operation of the user and responsible for receiving user input and processing it.
 * </p>
 *
 * @author Arun.
 * @version 1.0.
 */
public class PostController {

    private static final PostService POST_SERVICE = PostServiceImpl.getInstance();
    private static PostController postController = null;

    private PostController() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The post controller object.
     */
    public static PostController getInstance() {
        if (null == postController) {
            postController = new PostController();
        }

        return postController;
    }

    /**
     * <p>
     * Creates the user post.
     * </p>
     *
     * @param post Represents {@link Post} details.
     */
    public boolean create(final Post post) {
        return POST_SERVICE.create(post);
    }

    /**
     * <p>
     * Gets the all post of the user.
     * </p>
     *
     * @return The collection of post.
     */
    public Collection<Post> getAllPost() {
        return POST_SERVICE.getAllPost();
    }

    /**
     * <p>
     * Gets the post detail of the user.
     * </p>
     *
     * @param id Represents post id.
     * @return The post details of the user.
     */
    public Post getPost(final Long id) {
        return POST_SERVICE.getPost(id);
    }

    /**
     * <p>
     * Deletes the user post.
     * </p>
     *
     * @param id Represents post id.
     * @return True if post is deleted, false otherwise.
     */
    public boolean delete(final Long id) {
        return POST_SERVICE.delete(id);
    }

    /**
     * <p>
     * Updates the user post details.
     * </p>
     *
     * @param updatedPost Represents {@link Post} update details.
     */
    public boolean update(final Post updatedPost) {
        return POST_SERVICE.update(updatedPost);
    }

    /**
     * <p>
     * Gets a post of the user, if the post is posted by the user.
     * </p>
     *
     * @param userId Represents id of the user.
     * @param id Represents post id.
     * @return The post details of the user.
     */
    public Post getPost(final Long id, final Long userId) {
        return POST_SERVICE.getPost(id, userId);
    }
}

