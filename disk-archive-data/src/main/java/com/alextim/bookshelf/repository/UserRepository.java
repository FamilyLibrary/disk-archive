package com.alextim.bookshelf.repository;

import com.alextim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by админ on 29.05.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
}
