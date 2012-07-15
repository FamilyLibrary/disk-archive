package com.alextim.diskarchive.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.dao.impl.BasicDAO;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.utils.JSONHelper;

@Transactional
public class FilmGroupServiceImpl implements IFilmGroupService {
	private CoreDAOFactory coreDAOFactory;
	private JSONHelper jsonHelper;

    public static Comparator<FilmGroup> BY_GROUPNAME = new Comparator<FilmGroup>() {
        @Override
        public int compare(FilmGroup filmGroup1, FilmGroup filmGroup2) {
            String name1 = filmGroup1.getName();
            String name2 = filmGroup2.getName();

            int result = 0;
            if (name1 != null) {
                result = name1.compareTo(name2);
            } else if (name2 != null) {
                result = name2.compareTo(name1);
            }
            return result;
        }
    };

	
    public FilmGroupServiceImpl(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	@Override
	public List<FilmGroup> getFilmGroups(Comparator<FilmGroup> comporator) {
		List<FilmGroup> groups = new ArrayList<FilmGroup>(); 
		groups.addAll(coreDAOFactory.getFilmGroupDAO().findAll());
		
		if (comporator != null) {
		    Collections.sort(groups, comporator);
		}
		
		return groups;
	}
	
	@Override
	public void addGroup() {
		IFilmGroupDAO dao = coreDAOFactory.getFilmGroupDAO();
		dao.addGroup();
	}

    @SuppressWarnings("unchecked")
    @Override
    public void save(String jsonResult) {
        IFilmGroupDAO dao = this.coreDAOFactory.getFilmGroupDAO();
        FilmGroup entity = jsonHelper.unjson((BasicDAO<FilmGroup>) dao, jsonResult);
        coreDAOFactory.getFilmGroupDAO().saveOrUpdate(entity);
    }
    
    @Override
    public String convertToJSON(List<FilmGroup> groups) {
        return this.jsonHelper.json(groups);
    }

    public JSONHelper getJsonHelper() {
        return jsonHelper;
    }
    public void setJsonHelper(JSONHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

}
