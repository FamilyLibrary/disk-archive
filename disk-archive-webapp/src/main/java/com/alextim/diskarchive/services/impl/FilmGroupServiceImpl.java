package com.alextim.diskarchive.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.utils.JSONHelper;

@Service
@Transactional
public class FilmGroupServiceImpl implements IFilmGroupService {
    @Resource
	private ICoreDAOFactory coreDAOFactory;
    @Resource
    private JSONHelper jsonHelper;

    public static Comparator<FilmGroup> BY_GROUPNAME = (filmGroup1, filmGroup2) -> {
            String name1 = filmGroup1.getName();
            String name2 = filmGroup2.getName();

            int result = 0;
            if (name1 != null) {
                result = name1.compareTo(name2);
            } else if (name2 != null) {
                result = name2.compareTo(name1);
            }
            return result;
    };
	
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

    @Override
    public void save(String jsonResult) {
        IFilmGroupDAO dao = this.coreDAOFactory.getFilmGroupDAO();
        FilmGroup entity = jsonHelper.unjson(dao, jsonResult);
        coreDAOFactory.getFilmGroupDAO().addGroup(entity);
    }
    
    @Override
    public String convertToJSON(List<FilmGroup> groups) {
        return this.jsonHelper.json(groups);
    }
}
