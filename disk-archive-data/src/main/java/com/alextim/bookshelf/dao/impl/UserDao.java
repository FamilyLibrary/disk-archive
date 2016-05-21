package com.alextim.bookshelf.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.dao.IUserDao;
import com.alextim.dao.impl.BasicDAO;
import com.alextim.entity.User;

@Repository
@Transactional
public class UserDao extends BasicDAO<User> implements IUserDao {
    public void save(final User user) {
        saveOrUpdate(user);
    }
}
