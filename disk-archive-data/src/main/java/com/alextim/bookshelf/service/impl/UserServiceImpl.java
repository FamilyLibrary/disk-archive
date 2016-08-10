package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.bookshelf.service.IUserService;
import com.alextim.bookshelf.service.exception.UserNotFoundException;
import com.alextim.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 26.07.2016.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void changeActiveStatus(String login,boolean enabled) {
        User user = userRepository.findByLogin(login);
        user.setEnabled(enabled);
        userRepository.saveAndFlush(user);
    }


    @Override
    public void changePassword(String login, String password, String newPassword) throws UserNotFoundException {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("Old password shouldn't be empty or null");
        }

        User user = userRepository.findByLogin(login);
        if(user == null){
            throw new UserNotFoundException("User can't be null");
        }

        if(password.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userRepository.saveAndFlush(user);
        }else{
            throw new IllegalStateException ("New and old passwords are different");
        }
    }
}
