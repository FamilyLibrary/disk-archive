package com.alextim.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="USER_GROUPS")
public class UserGroup extends Group implements GrantedAuthority {
	private static final long serialVersionUID = 7200133857582903927L;

	@ManyToMany(fetch=FetchType.EAGER)
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

	@Override
	public String getAuthority() {
		return getName();
	}
}
