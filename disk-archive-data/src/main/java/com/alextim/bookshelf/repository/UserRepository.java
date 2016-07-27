package com.alextim.bookshelf.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.alextim.entity.User;

/**
 * Created by админ on 29.05.16.
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(String login);

}
