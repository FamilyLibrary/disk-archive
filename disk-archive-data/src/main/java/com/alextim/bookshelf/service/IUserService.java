package com.alextim.bookshelf.service;

import com.alextim.bookshelf.service.exception.UserCantBeNullException;
import com.alextim.entity.User;

/**
 * Created by admin on 26.07.2016.
 */
public interface IUserService {

    User addUser(User user);
    void delete(long id);
    User findByLogin(String login);
    void changeActiveStatus(String login, boolean enabled);
    void changePassword(String login, String password, String newPassword) throws UserCantBeNullException;

}
