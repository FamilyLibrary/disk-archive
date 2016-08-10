package com.alextim.diskarchive.controllers;

import com.alextim.bookshelf.service.IUserService;
import com.alextim.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by admin on 01.08.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/create", method = RequestMethod.GET)
    @ResponseBody
    public User createUser(){
        User user = new User();
        return user;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public void saveUser(User user) {
        userService.addUser(user);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteUser(Long id){
        userService.delete(id);
    }
}
