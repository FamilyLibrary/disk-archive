package com.alextim.bookshelf.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.bookshelf.service.IUserGroupService;
import com.alextim.bookshelf.service.IUserService;
import com.alextim.bookshelf.service.exception.UserAlreadyExistException;
import com.alextim.bookshelf.service.exception.UserNotFoundException;
import com.alextim.entity.User;
import com.alextim.entity.UserGroup;
import com.alextim.security.UserRole;

/**
 * Created by admin on 26.07.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserGroupService userGroupService;

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

    @Override
    public void register(String login, String password, UserRole userRole) throws UserAlreadyExistException {
        User user = userRepository.findByLogin(login);

        if(user != null){
            String info = String.format("User with login %s already exists", login);
            throw new UserAlreadyExistException(info);
        }
        user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEnabled(true);

        List<UserGroup> roles = userGroupService.findUserGroup(userRole);

        if(roles.size() == 0){
            roles.add(userGroupService.createUserGroup());
        }
        user.setUserGroups(roles);

        userRepository.saveAndFlush(user);
    }
}
