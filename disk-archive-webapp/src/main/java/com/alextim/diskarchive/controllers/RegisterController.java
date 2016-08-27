package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alextim.bookshelf.service.IUserService;
import com.alextim.entity.User;
import com.alextim.entity.UserGroup;

@Controller
public class RegisterController {
	@Autowired
	private IUserService userService;

	@RequestMapping(method=POST)
	@ResponseStatus(value=HttpStatus.OK)
    public void register(String login, String password, String _csrf) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEnabled(true);

        UserGroup userGroup = new UserGroup();
        userGroup.setName("ROLE_USER");

        user.setUserGroups(Arrays.asList(userGroup));

        userService.addUser(user);
    }
	
}
