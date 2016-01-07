package com.alextim.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alextim.Gender;

@Entity
@Table(name="PEOPLE")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements IEntity {
    public final static String NEW_FIRST_NAME = "new first name";
    public final static String NEW_LAST_NAME = "new last name";
    public final static Gender NEW_GENDER = Gender.NOT_KOWN;

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="GENDER")
    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @Column(name="BIRTHDAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar birthdayDate;

    @Override
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

    @Override
    public boolean equals(final Object personObj) {
        if (!(personObj instanceof Person)) {
            return false;
        }
        if (this == personObj) {
            return true;
        }

        final Person person = (Person)personObj;

        return (person.firstName == null ? this.firstName == null : person.firstName.equals(this.firstName)
                && person.lastName == null ? this.lastName == null : person.lastName.equals(this.lastName)
                && person.gender == null ? this.gender == null : person.gender.equals(this.gender)
                && person.birthdayDate == null ? this.birthdayDate == null : person.birthdayDate.equals(this.birthdayDate));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + (this.gender != null ? this.gender.hashCode() : 0);
        result = 31 * result + (this.birthdayDate != null ? this.birthdayDate.hashCode() : 0);
        return result;
    }

}
