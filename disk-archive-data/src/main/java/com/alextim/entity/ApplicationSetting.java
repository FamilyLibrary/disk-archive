package com.alextim.entity;

import javax.persistence.*;

/**
 * Created by admin on 13.09.2016.
 */
@Entity
@Table(name="APPLICATION_SETTINGS")

public class ApplicationSetting {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name="PROPERTY")
    private String property;

    @Column(name="VALUE")
    private String value;

    @Column(name="DESCRIPTION")
    private String description;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getProperty() {return property;}
    public void setProperty(String property) {this.property = property;}

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

}
