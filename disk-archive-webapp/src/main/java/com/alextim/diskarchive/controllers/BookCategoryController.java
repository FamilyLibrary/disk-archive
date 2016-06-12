package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.bookshelf.repository.BookGroupRepository;

@Controller
@RequestMapping(method=GET, path="/books/categories")
public class BookCategoryController {

	@Autowired
	private BookGroupRepository bookGroupRepository;

	@RequestMapping(method=GET)
    public ModelAndView getCategories() {
    	Iterable<BookGroup> groups = bookGroupRepository.findAll();
    	
        final ModelAndView mv = new ModelAndView();
        mv.addObject("groups", groups);

        return mv;
    }

}
