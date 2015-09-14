package com.alextim.diskarchive.utils.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.beanutils.PropertyUtils;

import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.IEntity;
import com.alextim.diskarchive.utils.JSONHelper;
import com.alextim.general.dao.IBasicDAO;

public class JSONHelperImpl implements JSONHelper {

    private ICoreDAOFactory coreDAOFactory;

    @Override
    public <T extends IEntity> T unjson(IBasicDAO<T> dao, String jsonString) {
        JSONObject array = (JSONObject) JSONSerializer.toJSON(jsonString);

        T object = null;
        for (Object keyObj : array.keySet()) {
            String key = keyObj.toString();
            JSONObject changes = (JSONObject) array.get(key);

            Long id = changes.getLong(IEntity.IDENTIFIER);
            object = dao.getById(id);

            if (object != null) {
                for (Object changeKeyObj : changes.keySet()) {
                    String name = changeKeyObj.toString();
                    if (IEntity.IDENTIFIER.equals(name)) {
                        continue;
                    }
                    Object value = changes.getString(name); // 12

                    try {
                        String[] nameParts = name.split("\\.");
                        String lastPart = nameParts[nameParts.length - 1];

                        if (nameParts.length > 1  && IEntity.IDENTIFIER.equals(lastPart)) {
                            String entityName = name.substring(0, name.lastIndexOf("."));

                            Object bean = PropertyUtils.getProperty(object, entityName);
                            Long entityId = Long.parseLong(value.toString());

                            if (bean instanceof IEntity) {
                                value = this.coreDAOFactory.getGenericDAO().getById(((IEntity) bean).getClass(), entityId);
                                name = entityName;
                            }
                        }

                        PropertyUtils.setProperty(object, name, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return object;
    }

    @Override
    public String json(List<? extends IEntity> entities, PropertyFilter filter) {
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        
        if (filter != null) {
        	config.setJsonPropertyFilter(filter);
        }

    	String rows = "[";
        for (Iterator<? extends IEntity> iterator = entities.iterator(); iterator.hasNext();) {
            IEntity entity = iterator.next();

            JSONObject obj = (JSONObject) JSONSerializer.toJSON(entity, config);
            String row = obj.toString();

            if (iterator.hasNext()) {
                row += ",";
            }
            rows += row;
        }
        rows += "]";

        return rows;
    }

	@Override
	public String json(List<? extends IEntity> entities) {
		return json(entities, null);
	}
    
    public ICoreDAOFactory getCoreDAOFactory() {
        return coreDAOFactory;
    }
    public void setCoreDAOFactory(ICoreDAOFactory coreDAOFactory) {
        this.coreDAOFactory = coreDAOFactory;
    }

}
