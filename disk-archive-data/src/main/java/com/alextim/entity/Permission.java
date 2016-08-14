package com.alextim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="PERMISSIONS")
public class Permission implements GrantedAuthority {
	private static final long serialVersionUID = 2818684977158400280L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="NAME")
    private String name;
    
    @Column(name="DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String getAuthority() {
		return getName();
	}
}
