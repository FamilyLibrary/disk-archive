package com.alextim.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_GROUPS")
public class UserGroup extends Group {
    @OneToMany
    private List<Permission> permissions;
    @OneToMany
    private List<User> users;
    
    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
