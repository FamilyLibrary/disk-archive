package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login.html")
public class LoginController {

    @RequestMapping(method=GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        final ModelAndView mv = new ModelAndView("WEB-INF/jsp/login.jsp");
        mv.addObject("title", "Login");
        return mv;
    }

    @RequestMapping(value="/register.html", method=GET)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        final ModelAndView mv = new ModelAndView("WEB-INF/jsp/register.jsp");
        return mv;
    }

}
