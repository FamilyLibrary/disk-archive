package com.alextim.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User extends Person {
    @Column(name="LOGIN")
    private String login;

    @Column(name="PASSWORD")
    private String password;

    private boolean enabled;

	@OneToMany
    private List<UserGroup> userGroups;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<UserGroup> getUserGroups() {
        return userGroups;
    }
    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
