package com.alextim.bookshelf.repository;

import com.alextim.bookshelf.repository.UserRepositoryCustom;
import com.alextim.entity.User;
import org.apache.poi.util.StringUtil;
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
        User user = userRepository.findByLogin(login);
        if(password.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userRepository.saveAndFlush(user);
        }else if (password.isEmpty()){
            throw new IllegalArgumentException("Old password shouldn't be empty or null");

        }else{
            throw new IllegalStateException ("New and old passwords are different");
        }
    }

}