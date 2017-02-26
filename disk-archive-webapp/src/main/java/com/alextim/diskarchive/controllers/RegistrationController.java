package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.alextim.bookshelf.service.exception.UserAlreadyExistException;
import com.alextim.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alextim.bookshelf.service.IUserService;


@Controller
@RequestMapping(path="/register")
public class RegistrationController {
	@Autowired
	private IUserService userService;

	@RequestMapping(path="register.html", method=POST)
	@ResponseStatus(value=HttpStatus.OK)
    public void register(String login, String password, String _csrf) throws UserAlreadyExistException {

		userService.register(login, password, UserRole.ROLE_USER);

	}
	
}
