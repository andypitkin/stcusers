package com.github.andypitkin.stcusers.users.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * A DTO describing the data that needs to be provided when making a user
 * creation request.
 * 
 */
public class UserCreationDTO {

    // ensure usernames can only be in the form of an email address so as to not get
    // any malicious strings
    @NotBlank
    @Email
    private final String username;
    // allowing all characters, database queries are relying on JPA which makes use
    // of paramaterised statements underneath
    // a theoretical frontend would need to sanitize these values for display to
    // ensure XSS attacks don't take place
    @NotBlank
    private final String firstName;
    // allowing all characters, database queries are relying on JPA which makes use
    // of paramaterised statements underneath
    // a theoretical frontend would need to sanitize these values for display to
    // ensure XSS attacks don't take place
    @NotBlank
    private final String lastName;

    /**
     * Creates a UserCreationDTO with the values provided
     * 
     * @param username  the username of the user you want to create
     * @param firstName the first name of the user you want to create
     * @param lastName  the last name of the user you want to create
     */
    public UserCreationDTO(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Retrieves the user name of the user you want to create
     * 
     * @return the user name of the user you want to create
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the first name of the user you want to create
     * 
     * @return the first name of the user you want to create
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the user you want to create
     * 
     * @return the last name of the user you want to create
     */
    public String getLastName() {
        return lastName;
    }
}
