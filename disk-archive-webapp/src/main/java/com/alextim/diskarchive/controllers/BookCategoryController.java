package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.bookshelf.repository.BookGroupRepository;
import com.alextim.diskarchive.dto.BookCategoryDto;
import com.alextim.diskarchive.dto.ResultDto;

@Controller
@RequestMapping(method = GET, path = "/books/categories")
public class BookCategoryController {

    @Autowired
    private BookGroupRepository bookGroupRepository;

    @RequestMapping(path="categories.json", method=GET)
    @ResponseBody
    public ResultDto<BookCategoryDto> getCategories() {
        Iterable<BookGroup> groups = bookGroupRepository.findAll();

        List<BookCategoryDto> result = new ArrayList<>();
        for (BookGroup group : groups) {
            for (BookGroup subCategory : group.getSubCategories()) {
                BookCategoryDto category = new BookCategoryDto();

                category.setSubcategoryId(subCategory.getId());
                category.setSubcategory(subCategory.getName());
                category.setCategoryId(group.getId());
                category.setCategory(group.getName());
                category.setDescription(group.getDescription());

                result.add(category);
            }
        }
        return new ResultDto<>(result, result.size());
    }

}
