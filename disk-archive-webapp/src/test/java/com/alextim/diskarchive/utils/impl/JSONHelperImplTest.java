package com.alextim.diskarchive.utils.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.util.PropertyFilter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.Author;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.entity.IEntity;
import com.alextim.general.dao.IBasicDAO;

public class JSONHelperImplTest {
    private Mockery context;
    
	private FilmGroup group;
	private Film film;

	@Before
    public void setUp() {
    	context = new Mockery();

		group = new FilmGroup();
		group.setId(1L);
		group.setName("group1");
		group.setDescription("group description1");
		
		film = new Film();
		film.setId(1L);
		film.setName("film1");
		film.setDescription("film description1");
		film.setFilmGroup(group); 
	}
	
	@Test
    public void testUnjson() {
		final ICoreDAOFactory factory = context.mock(ICoreDAOFactory.class);
		
		final IBasicDAO<Film> daoMock = context.mock(IBasicDAO.class);
		final IGenericDAO genericDAOMock = context.mock(IGenericDAO.class);
		

		JSONHelperImpl jsonHelper = new JSONHelperImpl();
		jsonHelper.setCoreDAOFactory(factory);
		
		final String result = 
				"{changes: {id: 1, name: 'film2', description: 'film description2', filmGroup.id: '1', filmGroup.name: 'group2', filmGroup.description: 'group description2'}}";
		context.checking(new Expectations() {{
			oneOf(daoMock).getById(1L);
			will(returnValue(film));
			oneOf(factory).getGenericDAO();
			will(returnValue(genericDAOMock));
			oneOf(genericDAOMock).getById(FilmGroup.class, 1L);
			will(returnValue(group));
		}});		
	
		Film entity = jsonHelper.unjson(daoMock, result);
		
		context.assertIsSatisfied();
		
		Assert.assertEquals("film2", entity.getName());
		Assert.assertEquals("film description2", entity.getDescription());
		Assert.assertEquals("group2", entity.getFilmGroup().getName());
		Assert.assertEquals("group description2", entity.getFilmGroup().getDescription());
		
		Assert.assertSame(1L, entity.getId());
		Assert.assertSame(1L, entity.getFilmGroup().getId());
    }
    
    @Test
    public void testJson() {
    	final PropertyFilter filterMock = context.mock(PropertyFilter.class);

		final Author author = new Author();
		author.setId(1L);
		author.setFilm(film);
		author.setName("author1");
		
		film.setAuthor(author);
    	
		context.checking(new Expectations() {{
			atLeast(1).of(filterMock).apply(with(any(IEntity.class)), with("image"), with(aNull(Object.class)));
			will(returnValue(true));
			atLeast(1).of(filterMock).apply(with(any(IEntity.class)), with(any(String.class)), with(any(Object.class)));
			will(returnValue(false));
			atLeast(1).of(filterMock).apply(with(any(IEntity.class)), with(any(String.class)), with(aNull(Object.class)));
			will(returnValue(false));
		}});		

    	
		final List<Film> entities = new ArrayList<Film>();
		entities.add(film);
		
		JSONHelperImpl jsonHelper = new JSONHelperImpl();
		String result = jsonHelper.json(entities, filterMock);
		
		context.assertIsSatisfied();
		
		Assert.assertEquals(
				"[" +
					"{" +
					 "\"actors\":[]," +
					 "\"author\":{" +
					 "\"film\":null," +
					 "\"films\":[]," +
					 "\"id\":1," +
					 "\"name\":\"author1\"" +
					 "}," +
					 "\"description\":\"film description1\"," +
					 "\"filmGroup\":{" +
					 "\"description\":\"group description1\"," +
					 "\"id\":1," +
					 "\"name\":\"group1\"}," +
					 "\"id\":1," +
					 "\"name\":\"film1\"}" +
				"]", 
			result);
    }
}
