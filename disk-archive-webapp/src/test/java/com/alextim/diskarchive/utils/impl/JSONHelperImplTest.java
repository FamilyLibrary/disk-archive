package com.alextim.diskarchive.utils.impl;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.alextim.diskarchive.dao.IBasicDAO;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.utils.JSONHelper;

public class JSONHelperImplTest {
    private Mockery context;

	@Before
    public void setUp() {
    	context = new Mockery();
    }
	
	@Test
    public void testUnjson() {
		final JSONHelper jsonMock = context.mock(JSONHelper.class);
		final IBasicDAO<Film> daoMock = context.mock(IBasicDAO.class);

		final Film entity = new Film();
		entity.setId(1L);
		entity.setName("film1");
		entity.setDescription("film description1");
		
		final String result = "{changes: {name: 'film1', description: 'film description1'}}";
		context.checking(new Expectations() {{
			oneOf(jsonMock).unjson(daoMock, result);
			will(returnValue(entity));
			/*oneOf(daoMock).getById(1L);
			will(returnValue(entity));*/
		}});
		
		jsonMock.unjson(daoMock, result);
		
		context.assertIsSatisfied();
    }
    
    @Test
    public void testJson() {        
		final JSONHelper jsonMock = context.mock(JSONHelper.class);		
		final List<Film> entities = new ArrayList<Film>();
		
		final String result = "test12";
		context.checking(new Expectations() {{
			oneOf(jsonMock).json(entities);
			will(returnValue(result));
		}});
		
		jsonMock.json(entities);
		
		context.assertIsSatisfied();
    }
}
