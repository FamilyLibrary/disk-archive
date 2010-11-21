package com.alextim.diskarchive.dwr.services.impl;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.beanutils.PropertyUtils;

import com.alextim.diskarchive.FilmInfoWrapper;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.dwr.services.IFilmRemoteService;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.IEntity;

public class FilmRemoteService implements IFilmRemoteService{

	private static final String IDENTIFIER = "id";
	private CoreDAOFactory coreDAOFactory;

	public FilmRemoteService(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	@Override
	public void addFilm() {
		Film film = new Film();
		
		film.setDescription("");
		film.setName("");
	
		coreDAOFactory.getFilmDAO().saveOrUpdate(film);
	}

	@Override
	public void deleteFilm(Long id) {
		Film film = coreDAOFactory.getFilmDAO().getById(id);
		coreDAOFactory.getFilmDAO().delete(film);
	}

	public FilmInfoWrapper filmInfo(Long id) {
		Film film = coreDAOFactory.getFilmDAO().getById(id);
		FilmInfoWrapper wrapper = new FilmInfoWrapper(film);
		return wrapper;
	}
	
	@Override
	public void save(String jsonResult) {
		JSONObject array = (JSONObject)JSONSerializer.toJSON(jsonResult);

		for (Object keyObj : array.keySet()) {
			String key = keyObj.toString();
			JSONObject changes = (JSONObject)array.get(key);
			
			Long filmId = changes.getLong(IDENTIFIER);
			Film film = coreDAOFactory.getFilmDAO().getById(filmId);

			for (Object changeKeyObj : changes.keySet()) {
				String name = changeKeyObj.toString();
				if (IDENTIFIER.equals(name)) {
					continue;
				}
				Object value = changes.getString(name); //12
				
				try {
					String[] nameParts = name.split("\\.");
					String lastPart = nameParts[nameParts.length - 1];
					
					if (nameParts.length > 1 && IDENTIFIER.equals(lastPart)) {
						String entityName = name.substring(0, name.lastIndexOf("."));
						
						Object bean = PropertyUtils.getProperty(film, entityName);
						Long entityId = Long.parseLong(value.toString());
						
						if (bean instanceof IEntity){
							value = coreDAOFactory.getGenericDAO().getById(((IEntity)bean).getClass(), entityId);
							name = entityName;
						}
					}  

					PropertyUtils.setProperty(film, name, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			
			coreDAOFactory.getFilmDAO().saveOrUpdate(film);
			
		}
	}
	

}
