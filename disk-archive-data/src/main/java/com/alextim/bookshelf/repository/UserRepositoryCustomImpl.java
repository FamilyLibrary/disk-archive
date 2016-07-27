package com.alextim.bookshelf.repository;

import com.alextim.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.*;
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
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("Old password shouldn't be empty or null");
        }

        User user = userRepository.findByLogin(login);
        if(password.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userRepository.saveAndFlush(user);
        }else{
            throw new IllegalStateException ("New and old passwords are different");
        }
    }

}