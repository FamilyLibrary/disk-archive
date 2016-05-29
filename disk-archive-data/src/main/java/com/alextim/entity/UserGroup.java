package com.alextim.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="USER_GROUPS")
public class UserGroup extends Group implements GrantedAuthority {
	private static final long serialVersionUID = 7200133857582903927L;

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

	@Override
	public String getAuthority() {
		return Long.toString(getId());
	}
}
