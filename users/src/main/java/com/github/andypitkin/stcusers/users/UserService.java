package com.github.andypitkin.stcusers.users;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.andypitkin.stcusers.users.dao.User;
import com.github.andypitkin.stcusers.users.dao.UserRespository;

/**
 * A service class for interacting with users. Provides basic CRUD operations on
 * the User entity.
 */
@Component
public class UserService {

    private UserRespository userRespository;

    /**
     * Creates the UserService backed by the UserRespository passed in.
     * 
     * @param userRespository the user repository backing the UserService.
     */
    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    /**
     * Retrieves an optional User object for the user id provided.
     * 
     * @param userId the id of the user you wish to retrieve
     * @return An Optional containing either the user if one exists for the id or
     *         empty if the id is invalid.
     */
    public Optional<User> getUserById(long userId) {
        return userRespository.findById(userId);
    }

    /**
     * Creates a user with the details provided.
     * 
     * @param username  the username of the user to create
     * @param firstName the first name of the user to create
     * @param lastName  the last name of the user to create
     * @return The new User entity object
     */
    public User createUser(String username, String firstName, String lastName) {
        return createOrUpdateUser(null, username, firstName, lastName);
    }

    /**
     * Creates or updates the user for the user id provided with the details
     * provided.
     * 
     * @param userId    the id of the user to update of null if its a creation call
     * @param username  the username of the user to create
     * @param firstName the first name of the user to create
     * @param lastName  the last name of the user to create
     * @return The new User entity object
     */
    public User createOrUpdateUser(Long userId, String username, String firstName, String lastName) {
        User user = null;

        if (userId != null) {
            user = userRespository.findById(userId).get();
        }

        if (user == null) {
            user = new User();
        }

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return userRespository.save(user);
    }
}
