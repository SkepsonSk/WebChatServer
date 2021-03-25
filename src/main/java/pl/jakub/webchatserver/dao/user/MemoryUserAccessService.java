package pl.jakub.webchatserver.dao.user;

import org.springframework.stereotype.Repository;
import pl.jakub.webchatserver.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides users data from DB.
 *
 * @author Jakub Zelmanowicz
 */
@Repository("memoryUserDao")
public class MemoryUserAccessService implements UserDao {

    /**
     * Represents an actual database.
     */
    private final static List<User> DB = new ArrayList<>();

    /**
     * Inserts a user into the DB list.
     *
     * @param id - id of the user,
     * @param user - user body object.
     * @return id of newly added user.
     */
    @Override
    public UUID insertPerson(UUID id, User user) {
        DB.add(new User(id, user.getName(), user.getToken()));
        return id;
    }

    /**
     * Gets all the users in DB list.
     *
     * @return list of users.
     */
    @Override
    public List<User> selectAll() {
        return DB;
    }

    /**
     * Selects a user with specific
     * id.
     *
     * @param id - id of the user.
     * @return optional user. It may not exist.
     */
    @Override
    public Optional<User> select(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    /**
     * Deletes all the users from
     * the DB list.
     *
     * @return true if operation was successful.
     * False otherwise.
     */
    @Override
    public boolean deleteAll() {
        DB.clear();
        return true;
    }

    /**
     * Deletes the specific user
     * identified by the certain id.
     *
     * @param id - id of the deleted user.
     * @return true if operation was successful.
     * False otherwise.
     */
    @Override
    public boolean delete(UUID id) {
        return DB.removeIf(user -> user.getId().equals(id));
    }
}
