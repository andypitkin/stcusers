package com.github.andypitkin.stcusers.users.dao;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * JPA User entity that defines the User in the system
 */
@Entity(name = "UserDetails")
public class User {
    
    @Convert(converter = EncryptedStringConverter.class)
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @Convert(converter = EncryptedStringConverter.class)
    private String firstName;
    @Convert(converter = EncryptedStringConverter.class)
    private String lastName;
    
    public User(String username, long userId, String firstName, String lastName) {
        this.username = username;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}

    /**
     * Retrieves the users username
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the users id
     * 
     * @return the user id of the user
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Retrieves the users first name
     * 
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the users last name
     * 
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user name of the user
     * 
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the user id of the user
     * 
     * @param userId the id of the user
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Sets the first name of the user
     * 
     * @param firstName the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user
     * 
     * @param lastName the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        User other = (User) obj;
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
