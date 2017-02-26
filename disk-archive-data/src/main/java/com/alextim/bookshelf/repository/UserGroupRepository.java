package com.alextim.bookshelf.repository;

import com.alextim.entity.Group;
import com.alextim.entity.User;
import com.alextim.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 26.08.2016.
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    UserGroup findByName(String name);
}
