package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.UserGroupRepository;
import com.alextim.bookshelf.service.IUserGroupService;
import com.alextim.entity.UserGroup;
import com.alextim.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 26.08.2016.
 */
@Service
public class UserGroupServiceImpl implements IUserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;


    @Override
    public List<UserGroup> findUserGroup(UserRole userRole) {
        List<UserGroup> list = new ArrayList<>();
        if(userRole == null){
            throw new IllegalArgumentException("userRole shouldn't be empty or null value");
        }

        final String roleName = userRole.name();
        final UserGroup role = userGroupRepository.findByName(roleName);

        if (role != null) {
            list.add(role);
        }
        return list;
    }

    @Override
    public UserGroup createUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(UserRole.ROLE_USER.name());
        return userGroup;
    }
}