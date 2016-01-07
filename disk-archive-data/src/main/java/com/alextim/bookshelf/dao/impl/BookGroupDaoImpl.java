package com.alextim.bookshelf.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.dao.IBookGroupDao;
import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.dao.impl.BasicDAO;

@Repository
@Transactional
public class BookGroupDaoImpl extends BasicDAO<BookGroup> implements IBookGroupDao {

    @Override
    public BookGroup addGroup() {
        final BookGroup bookGroup = createGroupEntity();

        bookGroup.setName(BookGroup.NEW_NAME);
        bookGroup.setDescription(BookGroup.NEW_DESCRIPTION);

        return addGroup(bookGroup);
    }

    @Override
    public BookGroup addGroup(final BookGroup bookGroup) {
        saveOrUpdate(bookGroup);
        return bookGroup;
    }

    protected BookGroup createGroupEntity() {
        return new BookGroup();
    }
}
