package com.alextim.diskarchive.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.ISeriesDAO;
import com.alextim.diskarchive.entity.Series;

@Transactional
public class SeriesDAO extends BasicDAO<Series> implements ISeriesDAO{

}
