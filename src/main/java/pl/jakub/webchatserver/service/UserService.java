package pl.jakub.webchatserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.jakub.webchatserver.dao.user.UserDao;
import pl.jakub.webchatserver.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides users data directly
 * from Data Access Objects.
 *
 * @author Jakub Zelmanowicz
 */
@Service
public class UserService {

    /**
     * Rooms access object.
     */
    private final UserDao userDao;

    /**
     * Creates a service basing on a User DAO.
     *
     * @param userDao - user dao.
     */
    @Autowired
    public UserService(@Qualifier("memoryUserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Adds a new user to the dao.
     *
     * @param user - user to add.
     * @return id of newly added user.
     */
    public UUID addUser(User user) {
        return userDao.insertPerson(user);
    }

    /**
     * Selects all users from the dao.
     *
     * @return list of users.
     */
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    /**
     * Gets the user identified by
     * the provided id.
     *
     * @param id - id of the user.
     * @return optional user. It may not exist.
     */
    public Optional<User> select(UUID id) {
        return userDao.select(id);
    }

    /**
     * Deletes all the data from dao.
     *
     * @return true if operation was successful. False otherwise.
     */
    public boolean deleteAll() {
        return userDao.deleteAll();
    }

    /**
     * Deletes the user identified by
     * the provided id.
     *
     * @param id - id of the user.
     * @return true if operation was successful. False otherwise.
     */
    public boolean delete(UUID id) {
        return userDao.delete(id);
    }

}
