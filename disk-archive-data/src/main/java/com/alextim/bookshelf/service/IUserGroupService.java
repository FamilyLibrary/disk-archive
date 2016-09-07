package com.alextim.bookshelf.service;

import com.alextim.entity.UserGroup;
import com.alextim.security.UserRole;

/**
 * Created by admin on 26.08.2016.
 */
public interface IUserGroupService {
    UserGroup findUserGroup(UserRole userRol);
    UserGroup createUserGroup();
}
