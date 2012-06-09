package com.alextim.diskarchive.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IAuthorDAO;
import com.alextim.diskarchive.entity.Author;

@Transactional
public class AuthorDAO extends BasicDAO<Author> implements IAuthorDAO{

}
