package com.alextim.entity;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.Gender;
import com.alextim.bookshelf.repository.UserRepository;
import com.alextim.diskarchive.configuration.ApplicationConfiguration;
import com.alextim.diskarchive.configuration.DataFactoryConfiguration;
import com.alextim.diskarchive.configuration.TestDaoConfiguration;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, DataFactoryConfiguration.class, TestDaoConfiguration.class}, 
                      inheritLocations = true)
@Transactional
@Rollback(false)
public class RegestrationFormTest {
    @Autowired
    private UserRepository userRepository;
    
    private final static String USER_FIRST_NAME = "Sergey";
    private final static String USER_LAST_NAME = "Timofeev";
    private final static Gender USER_GENDER = Gender.MALE;
    private final static String USER_LOGIN = "Sergtm";
    private final static String USER_PASSWORD = "111111";
    
    @Test
    @Rollback(true)
    public void saveUser() {
        final User user = new User();
        Calendar birthdayDate = GregorianCalendar.from(ZonedDateTime.now());

        user.setFirstName(USER_FIRST_NAME);
        user.setLastName(USER_LAST_NAME);
        user.setGender(USER_GENDER);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        user.setBirthdayDate(birthdayDate);

        userRepository.save(user);

        final User savedUser = userRepository.findOne(user.getId());

        Assert.assertEquals(USER_FIRST_NAME, savedUser.getFirstName());
        Assert.assertEquals(USER_LAST_NAME, savedUser.getLastName());
        Assert.assertEquals(USER_GENDER, savedUser.getGender());
        Assert.assertEquals(USER_LOGIN, savedUser.getLogin());
        Assert.assertEquals(USER_PASSWORD, savedUser.getPassword());
        Assert.assertEquals(birthdayDate, savedUser.getBirthdayDate());
    }
}
