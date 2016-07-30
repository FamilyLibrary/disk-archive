package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by admin on 30.07.2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String LOGIN = "stimofeev";
    private static final String PASSWORD = "111";
    private static final String NEW_PASSWORD = "222";

    @Mock
    private User user;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void shouldChangUserPassword(){
        when(userRepository.findByLogin(LOGIN)).thenReturn(user);
        when(user.getPassword()).thenReturn(PASSWORD);

        userService.changePassword(LOGIN, PASSWORD, NEW_PASSWORD);

        verify(user).setPassword(NEW_PASSWORD);
        verify(userRepository).saveAndFlush(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfPasswordNull(){
        userService.changePassword(LOGIN, null, null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIllegalStateException(){
        when(userRepository.findByLogin(LOGIN)).thenReturn(user);
        when(user.getPassword()).thenReturn(PASSWORD);

        userService.changePassword(LOGIN, "333", NEW_PASSWORD);
    }
}
