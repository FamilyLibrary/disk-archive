package com.alextim.bookshelf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import com.alextim.bookshelf.service.exception.UserAlreadyExistException;
import com.alextim.bookshelf.service.exception.UserNotFoundException;
import com.alextim.entity.User;
import com.alextim.security.UserRole;

/**
 * Created by admin on 26.07.2016.
 */
public interface IUserService {
    User addUser(User user);
    void delete(Long id);
    User findByLogin(String login);
    void changeActiveStatus(String login, boolean enabled);
    void changePassword(String login, String password, String newPassword) throws UserNotFoundException;
    void register(String login, String password, UserRole userRole) throws UserAlreadyExistException;

    Page<User> findPage(int page, int limit, String sort, Direction dir);
}
