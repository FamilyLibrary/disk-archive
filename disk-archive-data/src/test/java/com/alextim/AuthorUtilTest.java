package com.alextim;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.diskarchive.entity.Author;

@RunWith(MockitoJUnitRunner.class)
public class AuthorUtilTest {
    private List<Author> names1;
    private List<Author> names2;

    @Before
    public void setUp() {
        names1 = new ArrayList<Author>();
        names2 = new ArrayList<Author>();
    }

    @Test
    public void shouldCompareDifferentAuthors() {
        Author author1 = new Author();
        author1.setLastName("Alesha");

        Author author2 = new Author();
        author2.setLastName("Sergey");

        names1.add(author1);
        names2.add(author2);

        double percent = AuthorUtil.compareAuthors(names1, names2);

        Assert.assertEquals(100d, percent);
    }
    
    @Test
    public void shouldCompareSameAuthors() {
        Author author1 = new Author();
        author1.setLastName("Alesha");

        Author author2 = new Author();
        author2.setLastName("Alesha");

        names1.add(author1);
        names2.add(author2);
        
        double percent = AuthorUtil.compareAuthors(names1, names2);
        
        Assert.assertEquals(0d, percent);

    }
    
    @Test
    public void shouldCompareEmptyArrays() {
        double percent = AuthorUtil.compareAuthors(names1, names2);
        Assert.assertEquals(0d, percent);

    }
    @Test
    public void shouldCompareAuthors() {
        Author author1 = new Author();
        author1.setLastName("Alesha");

        Author author2 = new Author();
        author2.setLastName("Sergey");

        names1.add(author1);
        names1.add(author2);

        double percent = AuthorUtil.compareAuthors(names1, names2);
        
        Assert.assertEquals(0d, percent);

    }
}
