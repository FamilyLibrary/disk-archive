package com.alextim.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alextim.entity.User;

public class SimpleUserDetails implements UserDetails {
	private static final long serialVersionUID = 1238443405737827293L;

	private final User user;

	public SimpleUserDetails(final User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = user.getUserGroups()
				.stream()
				.flatMap(
					group -> group.getPermissions().stream()
				).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !user.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}