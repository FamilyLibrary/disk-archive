package com.alextim.bookshelf.dao;

import com.alextim.entity.User;

public interface IUserDao {
    void save(User user);
    public User getById(Long id);
}
