package pl.jakub.webchatserver.dao.user;

import pl.jakub.webchatserver.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Strategical interface for user data.
 * Provides an easy migration from any datasource.
 *
 * @author Jakub Zelmanowicz
 */
public interface UserDao {

    /**
     * Adds a new user to the datasource.
     *
     * @param id - id of the user,
     * @param user - user body object.
     * @return id of newly added user.
     */
    UUID insertPerson(UUID id, User user);

    /**
     * Adds a new user without id provided.
     *
     * @param user - user body object.
     * @return id of newly added user.
     */
    default UUID insertPerson(User user) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, user);
    }

    /**
     * Selects all users in the datasource.
     *
     * @return list of users.
     */
    List<User> selectAll();

    /**
     * Selects a user under provided
     * id.
     *
     * @param id - id of the user.
     * @return optional user. It may not exist.
     */
    Optional<User> select(UUID id);

    /**
     * Deletes all the users from
     * the datasource.
     *
     * @return true if operation was successful. False otherwise.
     */
    boolean deleteAll();

    /**
     * Deletes a user identified by
     * the provided id,
     *
     * @param id - id of user to delete.
     * @return true if operation was successful. False otherwise.
     */
    boolean delete(UUID id);

}
