package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alextim.bookshelf.service.IUserService;
import com.alextim.diskarchive.dto.ResultDto;
import com.alextim.entity.User;


/**
 * Created by admin on 01.08.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(path="users.json", method=GET)
    @ResponseBody
    public ResultDto<User> getUsers(int page, int start, int limit, 
            @RequestParam(required=false) String sort, @RequestParam(required=false) Direction dir) {
        Page<User> users = userService.findPage(page, limit, sort, dir);
        return new ResultDto<User>(users.getContent(), users.getTotalElements());
    }

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
