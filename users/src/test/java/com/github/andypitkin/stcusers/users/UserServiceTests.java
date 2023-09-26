package com.github.andypitkin.stcusers.users;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.andypitkin.stcusers.users.dao.User;
import com.github.andypitkin.stcusers.users.dao.UserRespository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class UserServiceTests {

    @Autowired
    private UserRespository userRespository;

    @Test
    void getUserByIdShouldRetrievePreviouslyCreatedUser() {
        UserService userService = new UserService(userRespository);
        User createdUser1 = userService.createUser("username", "firstname", "lastname");
        User createdUser2 = userService.createUser("username2", "firstname2", "lastname2");
        User createdUser3 = userService.createUser("username3", "firstname3", "lastname3");

        Optional<User> retrievedFirstUser = userService.getUserById(createdUser1.getUserId());
        assertThat(retrievedFirstUser).contains(createdUser1);

        Optional<User> retrievedSecondUser = userService.getUserById(createdUser2.getUserId());
        assertThat(retrievedSecondUser).contains(createdUser2);

        Optional<User> retrievedThirdUser = userService.getUserById(createdUser3.getUserId());
        assertThat(retrievedThirdUser).contains(createdUser3);
    }

    @Test
    void getUserByIdShouldReturnEmptyIfUserIdNotFound() {
        UserService userService = new UserService(userRespository);
        User createdUser1 = userService.createUser("username", "firstname", "lastname");

        Optional<User> userThatDoesntExist = userService.getUserById(createdUser1.getUserId()+1);
        assertThat(userThatDoesntExist).isEmpty();
    }
}
