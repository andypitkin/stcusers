package com.github.andypitkin.stcusers.users.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * The UserRepository interface for dealing with interactions between the
 * persistence layer and the services for the User entity
 */
public interface UserRespository extends CrudRepository<User, Long> {

}
