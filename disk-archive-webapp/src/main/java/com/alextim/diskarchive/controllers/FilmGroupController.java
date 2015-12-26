package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.services.impl.FilmGroupServiceImpl;

@Controller
@RequestMapping("/filmGroup.html")
public class FilmGroupController extends MultiActionController {
    @Resource
    private IFilmGroupService filmGroupService;

    @RequestMapping(method=GET)
    public ModelAndView filmGroup(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("WEB-INF/jsp/filmGroup.jsp");

        List<FilmGroup> groups = filmGroupService.getFilmGroups(FilmGroupServiceImpl.BY_GROUPNAME);
        String rows = filmGroupService.convertToJSON(groups);
        
        mv.addObject("title", "Film Groups");
        mv.addObject("rows", rows);
        return mv;
    }
}
