package com.alextim.diskarchive.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookGroup;
import com.alextim.diskarchive.entity.Actor;
import com.alextim.diskarchive.entity.Author;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.entity.Series;

@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {
    private static final Class<?>[] ANNOTATED_CLASSES = new Class[] {
            Film.class, FilmGroup.class, Series.class, Author.class,
            Actor.class, Book.class, BookGroup.class };

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PropertiesFactoryBean hibernateProperties;

    @Bean(name = "sessionFactory")
    public AnnotationSessionFactoryBean sessionFactory() throws IOException {
        final AnnotationSessionFactoryBean sessionFactoryBean = new AnnotationSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(ANNOTATED_CLASSES);

        sessionFactoryBean
                .setHibernateProperties((Properties) hibernateProperties
                        .getObject());

        return sessionFactoryBean;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public PlatformTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setDataSource(dataSource);
        tm.setSessionFactory(sessionFactory);
        return tm;
    }
}
