package com.alextim.bookshelf.repository;

/**
 * Created by admin on 26.07.2016.
 */
public interface UserRepositoryCustom {
    public void changeActiveStatus(String login, boolean enabled);

    public void changePassword(String login, String password, String newPassword);
}
