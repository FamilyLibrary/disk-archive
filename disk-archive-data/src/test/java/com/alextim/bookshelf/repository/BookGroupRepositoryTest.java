package com.alextim.bookshelf.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.diskarchive.configuration.ApplicationConfiguration;
import com.alextim.diskarchive.configuration.TestDaoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, TestDaoConfiguration.class})
public class BookGroupRepositoryTest {
	@Autowired
	public BookGroupRepository bookGroupRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void shouldSaveAndCommit() {
		final BookGroup bookGroup = new BookGroup();
		bookGroup.setDescription("TestGroup");
		bookGroupRepository.saveAndCommit(bookGroup);
	}
}
