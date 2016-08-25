package com.alextim.entity;

import javax.persistence.*;

@Entity
@Table(name="GROUPS")
@Inheritance(strategy=InheritanceType.JOINED)
public class Group implements IEntity {
	public final static String NEW_NAME = "new group name";
	public final static String NEW_DESCRIPTION = "new group description";

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOK_GROUPS_SEQUENCE")
	@SequenceGenerator(name="BOOK_GROUPS_SEQUENCE", sequenceName="SEQ_BOOK_GROUPS")
	private Long id;
	
	private String name;
	private String description;

	@Override
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
		return description == null ? "" : description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
