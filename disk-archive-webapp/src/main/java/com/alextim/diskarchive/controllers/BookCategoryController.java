package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.bookshelf.repository.BookGroupRepository;

@Controller
@RequestMapping(method = GET, path = "/books/categories")
public class BookCategoryController {

    @Autowired
    private BookGroupRepository bookGroupRepository;

    @RequestMapping(method = GET)
    public ModelAndView getCategories() {
        Iterable<BookGroup> groups = bookGroupRepository.findAll();

        // List<BookGroup> bg = new ArrayList<BookGroup>();
        JSONArray result = new JSONArray();
        for (BookGroup group : groups) {
            for (BookGroup subCategory : group.getSubCategories()) {
                JSONObject jo = new JSONObject();
                jo.put("subcategoryId", subCategory.getId());
                jo.put("subcategory", subCategory.getName());
                jo.put("categoryId", group.getId());
                jo.put("category", group.getName());
                jo.put("description", group.getDescription());
                
                result.add(jo);
            }
        }

        final ModelAndView mv = new ModelAndView("/WEB-INF/jsp/categories/list.jsp");
        mv.addObject("json", result.toString());

        return mv;
    }

}
