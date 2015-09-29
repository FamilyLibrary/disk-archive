package com.alextim.general.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alextim.diskarchive.entity.IEntity;
import com.alextim.general.Gender;

@Entity
@Table(name="PEOPLE")
public class Person implements IEntity {
    public final static String NEW_FIRST_NAME = "new first name";
    public final static String NEW_LAST_NAME = "new last name";
    public final static Gender NEW_GENDER = Gender.NOT_KOWN;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="gener")
    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @Column(name="birthdayDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar birthdayDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getBirthdayDate() {
        return birthdayDate;
    }
    public void setBirthdayDate(Calendar birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
