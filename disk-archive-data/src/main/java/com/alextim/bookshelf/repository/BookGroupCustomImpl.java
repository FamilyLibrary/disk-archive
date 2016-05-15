package com.alextim.bookshelf.repository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.dao.impl.BasicDAO;

public class BookGroupCustomImpl extends BasicDAO<BookGroup> implements BookGroupCustom {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveAndCommit(final BookGroup bookGroup) {
		saveOrUpdate(bookGroup);
	}

}
