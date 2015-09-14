package com.alextim.diskarchive.utils;

import java.util.List;

import net.sf.json.util.PropertyFilter;

import com.alextim.diskarchive.entity.IEntity;
import com.alextim.general.dao.IBasicDAO;

public interface JSONHelper {
    <T extends IEntity> T unjson(IBasicDAO<T> dao, String jsonString);
    
    String json(List<? extends IEntity> entities, PropertyFilter filter);
    String json(List<? extends IEntity> entities);
}
