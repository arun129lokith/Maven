package com.instagram.dao.impl;

import com.instagram.dao.LikeDao;
import com.instagram.database.DataBaseConnectionPool;
import com.instagram.model.Like;
import com.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Implements the data base service of like related operation.
 * </p>
 *
 * @author Arun.
 * @version 1.1.
 */
public class LikeDaoImpl implements LikeDao {

    private static LikeDaoImpl likeDaoImpl;
    private static final DataBaseConnectionPool CONNECTION_POOL = DataBaseConnectionPool.getInstance();

    private LikeDaoImpl() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The like database service implementation object.
     */
    public static LikeDaoImpl getInstance() {
        return null == likeDaoImpl ? likeDaoImpl = new LikeDaoImpl() : likeDaoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like Represents {@link Like} details.
     */
    @Override
    public void likePost(final Like like) {
        final String query = "INSERT INTO LIKES (POST_ID, USER_ID) VALUES (?, ?)";

        try (final Connection connection = CONNECTION_POOL.get();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, like.getPostId());
            preparedStatement.setLong(2, like.getUserId());

            preparedStatement.executeUpdate();
            CONNECTION_POOL.releaseConnection(connection);
        } catch (final SQLException | InterruptedException message) {
            System.out.println(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents like id.
     * @return True if like is removed, false otherwise.
     */
    @Override
    public boolean unlikePost(final Long id) {
        final String query = "DELETE FROM LIKES WHERE ID = ?";

        try (final Connection connection = CONNECTION_POOL.get();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            CONNECTION_POOL.releaseConnection(connection);

            if (0 < preparedStatement.executeUpdate()) {
                return true;
            }
        } catch (final SQLException | InterruptedException message) {
            System.out.println(message.getMessage());
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id.
     * @return The collection of user.
     */
    @Override
    public Collection<User> getLikeUser(final Long postId) {
        final Collection<User> users = new ArrayList<>();
        final String query = "SELECT U.NAME FROM USERS AS U INNER JOIN LIKES AS L ON U.ID = L.USER_ID WHERE L.POST_ID = ?";

        try (final Connection connection = CONNECTION_POOL.get();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final User user = new User();

                user.setName(resultSet.getString("NAME"));
                users.add(user);
            }
            CONNECTION_POOL.releaseConnection(connection);
        } catch (final SQLException | InterruptedException message) {
            System.out.println(message.getMessage());
        }

        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id.
     * @return The count of the like.
     */
    @Override
    public Long getLikeCount(final Long postId) {
        final String query = "SELECT COUNT(POST_ID) AS LIKE_COUNT FROM LIKES WHERE POST_ID = ? GROUP BY POST_ID";

        try (final Connection connection = CONNECTION_POOL.get();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            CONNECTION_POOL.releaseConnection(connection);

            if (resultSet.next()) {
                return resultSet.getLong("LIKE_COUNT");
            }
        } catch (final SQLException | InterruptedException message) {
            System.out.println(message.getMessage());
        }

        return null;
    }
}
