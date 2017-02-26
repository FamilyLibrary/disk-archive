package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.UserGroupRepository;
import com.alextim.entity.UserGroup;
import com.alextim.security.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by admin on 27.08.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserGroupServiceImplTest {
        private static final String ROLE_USER = "ROLE_USER" ;
    @Mock
    private UserGroupRepository userGroupRepository;
    @InjectMocks
    UserGroupServiceImpl userGroupService = new UserGroupServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowNullPointerExceptionIfUserRoleNull(){
        userGroupService.findUserGroup(null);
    }
    @Test
    public void shouldReturnRoleUser(){

        List<UserGroup> list = new ArrayList<>();

        UserGroup userGroup = new UserGroup();
        userGroup.setName(ROLE_USER);
        userGroup.setId(1L);

        when(userGroupRepository.findByName(ROLE_USER)).thenReturn(userGroup);
        List<UserGroup> groups = userGroupService.findUserGroup(UserRole.ROLE_USER);
            assertTrue(groups.get(0).getId() == 1L);
            assertEquals(groups.get(0).getName(), ROLE_USER);
    }
}
