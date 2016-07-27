package com.alextim.bookshelf.repository;

import com.alextim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by админ on 29.05.16.
 */
public interface UserRepository extends JpaRepository<User, Long>,UserRepositoryCustom {
	User findByLogin(String login);
}
