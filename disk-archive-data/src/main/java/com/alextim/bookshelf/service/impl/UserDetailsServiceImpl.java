package com.alextim.bookshelf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.entity.User;
import com.alextim.security.SimpleUserDetails;

/**
 * Created by админ on 29.05.16.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(login);
        if (user == null) {
        	throw new UsernameNotFoundException(String.format("Could not find user by login %s",  login));
        }
        final UserDetails userDetails = new SimpleUserDetails(user);
    	return userDetails;
    }
}
