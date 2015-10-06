package com.alextim.bookshelf.service.impl;

import java.util.List;

import com.alextim.bookshelf.entity.BookAuthor;

public class AbsentVolumesResult {
    private BookAuthor bookAuthor;
    private List<Integer> absentVolumes;

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public List<Integer> getAbsentVolumes() {
        return absentVolumes;
    }
    public void setAbsentVolumes(List<Integer> absentVolumes) {
        this.absentVolumes = absentVolumes;
    }
}
