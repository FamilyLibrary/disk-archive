package com.alextim.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.entity.User;
import com.alextim.security.SimpleUserDetails;

/**
 * Created by админ on 29.05.16.
 */
public class UserDetailsServiceImpl extends JdbcDaoImpl {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected List<UserDetails> loadUsersByUsername(String login) {
    	final User user = findUserByLogin(login);
        return Arrays.asList(new SimpleUserDetails(user));
    }

    @Override
	protected List<GrantedAuthority> loadGroupAuthorities(String login) {
    	final User user = findUserByLogin(login);
    	List<GrantedAuthority> groups = new ArrayList<>(user.getUserGroups());
    	return groups;
    }

    private User findUserByLogin(String login) {
        final User user = userRepository.findByLogin(login);
        if (user == null) {
        	throw new UsernameNotFoundException(String.format("Could not find user by login %s",  login));
        }
        return user;
	}
}
