package com.alextim.diskarchive.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {
    private static final String DISK_ARCHIVE_ANNOTATED_PACKAGES = "com.alextim.diskarchive.entity";
    private static final String BOOK_SHELF_ANNOTATED_PACKAGES = "com.alextim.bookshelf.entity";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PropertiesFactoryBean hibernateProperties;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(DISK_ARCHIVE_ANNOTATED_PACKAGES, BOOK_SHELF_ANNOTATED_PACKAGES);

        sessionFactoryBean.setHibernateProperties((Properties) hibernateProperties.getObject());

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
