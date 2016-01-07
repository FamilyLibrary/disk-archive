package com.alextim.bookshelf.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.dao.ICompleteWorkDao;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.dao.impl.BasicDAO;

@Repository
@Transactional
public class CompleteWorkDaoImpl extends BasicDAO<CompleteWork> implements ICompleteWorkDao {

}
