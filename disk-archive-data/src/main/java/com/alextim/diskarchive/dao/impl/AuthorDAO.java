package com.alextim.diskarchive.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.impl.BasicDAO;
import com.alextim.diskarchive.dao.IAuthorDAO;
import com.alextim.diskarchive.entity.Author;

@Repository
@Transactional
public class AuthorDAO extends BasicDAO<Author> implements IAuthorDAO{

}
