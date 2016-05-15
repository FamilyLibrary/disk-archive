package com.alextim.bookshelf.repository;

import com.alextim.bookshelf.entity.BookGroup;

public interface BookGroupCustom {
	void saveAndCommit(BookGroup bookGroup);
}
