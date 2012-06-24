package com.alextim.diskarchive.utils;

import java.util.List;

import com.alextim.diskarchive.dao.IBasicDAO;
import com.alextim.diskarchive.entity.IEntity;

public interface JSONHelper {
    <T extends IEntity> T unjson(IBasicDAO<T> dao, String jsonString);
    String json(List<? extends IEntity> entities);
}
