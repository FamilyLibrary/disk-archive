package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;

@Controller
@RequestMapping(method=GET, path="/books")
public class BookController {

    @Autowired
    private IBookService bookService;
    
    @RequestMapping(method=GET)
    public ModelAndView getBooks() {
        Collection<Book> books = bookService.findAll();
        
        final ModelAndView mv = new ModelAndView("/WEB-INF/jsp/books/list.jsp");
        mv.addObject("books", books);

        return mv;
    }
    
    @RequestMapping(method=GET, path="detail.html")
    public ModelAndView getBook(Long id) {
        Book book = bookService.getById(id);
        
        final ModelAndView mv = new ModelAndView("/WEB-INF/jsp/books/detail.jsp");
        mv.addObject("book", book);

        return mv;
    }
}
