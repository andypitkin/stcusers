package com.github.andypitkin.stcusers.users.web;

import com.github.andypitkin.stcusers.users.dao.User;

/**
 * A DTO describing the data that would be returned when querying a user.
 * 
 */
public class UserDTO {

    private final String username;
    private final long userId;
    private final String firstName;
    private final String lastName;
    
    /**
     * Creates a UserDTO with the values provided.
     * 
     * @param username the username of the user
     * @param userId the user id of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     */
    public UserDTO(String username, long userId, String firstName, String lastName) {
        this.username = username;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Retrieves the user name of the user
     * 
     * @return the user name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the user id of the user
     * 
     * @return the user id of the user
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Retrieve the first name of the user
     * 
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the user
     * 
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Transforms the User entity to the UserDTO object.
     * 
     * @param user the user entity to transform
     * @return A UserDTO describing the user passed in
     */
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getUsername(), user.getUserId(), user.getFirstName(), user.getLastName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (userId != other.userId)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    
}
