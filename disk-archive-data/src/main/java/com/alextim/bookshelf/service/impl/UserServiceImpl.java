package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 26.07.2016.
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void changeActiveStatus(String login, boolean enabled) {
         userRepository.changeActiveStatus( login, enabled);
    }

    @Override
    public void changePassword(String login, String password, String newPassword) {
        userRepository.changePassword(login, password, newPassword);
    }
}
