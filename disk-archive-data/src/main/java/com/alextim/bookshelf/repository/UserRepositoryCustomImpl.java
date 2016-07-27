package com.alextim.bookshelf.repository;

import com.alextim.bookshelf.repository.UserRepositoryCustom;
import com.alextim.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 26.07.2016.
 */
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void changeActiveStatus(String login,boolean enabled) {
        User user = userRepository.findByLogin(login);
        user.setEnabled(enabled);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void changePassword(String login, String password, String newPassword) {
        User user = userRepository.findByLogin(login);
        if(password.equals(newPassword)) {
            user.setPassword(newPassword);
            userRepository.saveAndFlush(user);
        }else{
                throw new NullPointerException();
        }
    }

}