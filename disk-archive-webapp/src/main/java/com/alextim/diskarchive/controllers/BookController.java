package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;
import com.alextim.diskarchive.dto.ResultDto;

@Controller
@RequestMapping(method=GET, path="/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @RequestMapping(path="books.json", method=GET)
    @ResponseBody
    public ResultDto<Book> getBooks(int page, int start, int limit, 
            @RequestParam(required=false) String sort, @RequestParam(required=false) Direction dir) {
        Page<Book> books = bookService.findPage(page, limit, sort, dir);
        return new ResultDto<Book>(books.getContent(), books.getTotalElements());
    }
    
    @RequestMapping(path="detail.html", method=GET)
    public ModelAndView getBook(Long id) {
        Book book = bookService.getById(id);
        
        final ModelAndView mv = new ModelAndView("/WEB-INF/jsp/books/detail.jsp");
        mv.addObject("book", book);

        return mv;
    }
}