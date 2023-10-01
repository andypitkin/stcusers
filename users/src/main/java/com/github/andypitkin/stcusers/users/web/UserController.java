package com.github.andypitkin.stcusers.users.web;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.andypitkin.stcusers.users.UserService;
import com.github.andypitkin.stcusers.users.dao.User;

import jakarta.validation.Valid;

/**
 * A RestController class that provides the rest interface for interacting with
 * the Users entity.
 * 
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a UserDTO in the ResponseEntity body for the user defined by the id
     * passed into the method.
     * 
     * @param userId the id of the user to retrieve
     * @return Returns the UserDTO in the body of the ResponseEntity unless the user
     *         is not found in which case the ResponseEntity will contain the
     *         NotFound status code.
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user.map(UserDTO::fromUser).get());
    }

    /**
     * Creates a new user with the details provided in the userCreationRequest
     * parameter
     * 
     * @param userCreationRequest the details of the user to create
     * @return Returns a ResponseEntity with the location header set to the location
     *         of the newly created user.
     */
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreationDTO userCreationRequest) {
        User newUser = userService.createUser(userCreationRequest.getUsername(), userCreationRequest.getFirstName(),
                userCreationRequest.getLastName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getUserId()).toUri();

        return ResponseEntity.created(location).build();
    }
}