package com.alextim.diskarchive.utils;

import java.util.List;

import com.alextim.diskarchive.dao.impl.BasicDAO;
import com.alextim.diskarchive.entity.IEntity;

public interface JSONHelper {
    <T extends IEntity> T unjson(BasicDAO<T> dao, String jsonString);
    String json(List<? extends IEntity> entities);
}
