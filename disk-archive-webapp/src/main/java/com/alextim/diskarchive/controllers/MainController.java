package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.bookshelf.repository.BookGroupRepository;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.services.IFilmService;
import com.alextim.diskarchive.services.impl.FilmGroupServiceImpl;
import com.alextim.diskarchive.treeView.Root;
import com.alextim.diskarchive.treeView.TreeNode;

@RestController
@RequestMapping(path="/main")
public class MainController {
    private final Logger LOG = Logger.getLogger(MainController.class);

    private final String IMAGE_NAME = "/images/nophoto-thumb.gif";

    @Resource
    private BookGroupRepository bookGroupRepository;

    @Resource
    private IFilmService filmService;
    @Resource
    private IFilmGroupService filmGroupService;

    @RequestMapping(path = "treeView.json", method=GET)
    @ResponseBody
    public Root treeView(){
        Iterable<BookGroup> bookGroups = bookGroupRepository.findAll();
        List<TreeNode> treeNodes = new ArrayList<>();

        for (BookGroup bookGroup : bookGroups) {
            treeNodes.add(new TreeNode(bookGroup.getName()));
        }
        TreeNode booksNode = new TreeNode("Books", false, treeNodes);
        TreeNode settingsNode = new TreeNode("Settings", false, Arrays.asList(new TreeNode[]{}));

        return new Root("Root", true, Arrays.asList(booksNode, settingsNode));
    }

    @RequestMapping(value="renderGeneralImage.html", method=GET)
    public ModelAndView renderGeneralImage(HttpServletRequest request,
            HttpServletResponse response) {
        String filmIdParam = request.getParameter("filmId");
        Long filmId = Long.parseLong(filmIdParam);

        Film film = filmService.getById(filmId);
        byte[] imageArray = film.getImage();

        try {
            if (ArrayUtils.isEmpty(imageArray)) {
                InputStream stream = getClass().getResourceAsStream(IMAGE_NAME);
                int size = stream.available();

                imageArray = new byte[size];
                
                stream.read(imageArray, 0, size);
                stream.close();
            }
            response.getOutputStream().write(imageArray);
            response.setContentType("application/octet-stream");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return null;
    }

    @RequestMapping(method=GET)
    public ModelAndView main(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("WEB-INF/jsp/main.jsp");
    }
}
