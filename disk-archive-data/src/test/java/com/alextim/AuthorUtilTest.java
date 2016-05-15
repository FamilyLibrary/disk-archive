package com.alextim;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.diskarchive.entity.Author;

@RunWith(MockitoJUnitRunner.class)
public class AuthorUtilTest {
    @Test
    public void shouldCompareDifferentAuthors() {
        Author author1 = new Author();
        author1.setLastName("Alesha");

        Author author2 = new Author();
        author2.setLastName("Sergey");

        ArrayList<Author> names1 = new ArrayList<Author>();
        names1.add(author1);

        ArrayList<Author> names2 = new ArrayList<Author>();
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

        ArrayList<Author> names1 = new ArrayList<Author>();
        names1.add(author1);

        ArrayList<Author> names2 = new ArrayList<Author>();
        names2.add(author2);
        
        double percent = AuthorUtil.compareAuthors(names1, names2);
        
        Assert.assertEquals(0d, percent);

    }
    
    @Test
    public void shouldCompareEmptyArrays() {

        ArrayList<Author> names1 = new ArrayList<Author>();
      
        ArrayList<Author> names2 = new ArrayList<Author>();
        
        double percent = AuthorUtil.compareAuthors(names1, names2);
        
        Assert.assertEquals(0d, percent);

    }
    @Test
    public void shouldCompareAuthors() {
        Author author1 = new Author();
        author1.setLastName("Alesha");

        Author author2 = new Author();
        author2.setLastName("Sergey");

        ArrayList<Author> names1 = new ArrayList<Author>();
        names1.add(author1);
        names1.add(author2);

        ArrayList<Author> names2 = new ArrayList<Author>();
        
        double percent = AuthorUtil.compareAuthors(names1, names2);
        
        Assert.assertEquals(0d, percent);

    }
    
}
