package com.alextim.diskarchive.utils;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.IEntity;

public class JSONHelper {
	public static String convertToJSON(List<? extends IEntity> entities) {
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (source instanceof Film && "image".equals(name)) {
					return true;
				}
				return false;
			}
		});

		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
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
}
