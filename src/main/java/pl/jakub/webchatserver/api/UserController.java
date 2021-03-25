package pl.jakub.webchatserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.webchatserver.model.User;
import pl.jakub.webchatserver.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * Provides an HTTP gateway for
 * managing users.
 *
 * @author Jakub Zelmanowicz
 */
@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all the users.
     *
     * @return list of users.
     */
    @GetMapping
    public List<User> selectAll() {
        return userService.selectAll();
    }

    /**
     * Gets the user identified by the
     * specific id.
     *
     * @param id - id of the user.
     * @return user object. May return null.
     */
    @GetMapping(path = "{id}")
    public User select(@PathVariable("id") UUID id) {
        return userService.select(id)
                .orElse(null);
    }

    /**
     * Adds a user to the DAO.
     *
     * @param user - user data object.
     * @return added user object.
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        UUID id = userService.addUser(user);
        return userService.select(id).get();
    }

    /**
     * Deletes all the users.
     *
     * @return true if operation was successful.
     * False otherwise.
     */
    @DeleteMapping
    public boolean deleteAll() {
        return userService.deleteAll();
    }

    /**
     * Deletes the user identified
     * by a specific id.
     *
     * @param id - id of the user.
     * @return true if operation was successful.
     * False otherwise.
     */
    @DeleteMapping(path = "{id}")
    public boolean delete(@PathVariable("id") UUID id) {
        return userService.delete(id);
    }

}
