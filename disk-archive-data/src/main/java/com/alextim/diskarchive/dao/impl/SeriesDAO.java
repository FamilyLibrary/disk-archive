package com.alextim.diskarchive.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.impl.BasicDAO;
import com.alextim.diskarchive.dao.ISeriesDAO;
import com.alextim.diskarchive.entity.Series;

@Repository
@Transactional
public class SeriesDAO extends BasicDAO<Series> implements ISeriesDAO{

}
