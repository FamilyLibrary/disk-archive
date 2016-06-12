package com.alextim.diskarchive.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"com.alextim"})
@EnableJpaRepositories(basePackages={"com.alextim.bookshelf.repository"})
public class ApplicationConfiguration {
    private static final String DISK_ARCHIVE_ANNOTATED_PACKAGES = "com.alextim.diskarchive.entity";
    private static final String BOOK_SHELF_ANNOTATED_PACKAGES = "com.alextim.bookshelf.entity";
    private static final String ENTITY_ANNOTATED_PACKAGES = "com.alextim.entity";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PropertiesFactoryBean hibernateProperties;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        /*final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(DISK_ARCHIVE_ANNOTATED_PACKAGES, BOOK_SHELF_ANNOTATED_PACKAGES, ENTITY_ANNOTATED_PACKAGES);

        sessionFactoryBean.setHibernateProperties((Properties) hibernateProperties.getObject());
        sessionFactoryBean.setEntityInterceptor(new EntityTimestampInterceptor());

        return sessionFactoryBean;*/
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(DISK_ARCHIVE_ANNOTATED_PACKAGES, BOOK_SHELF_ANNOTATED_PACKAGES, ENTITY_ANNOTATED_PACKAGES);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties((Properties) hibernateProperties.getObject());
   
        return em;
    }

    @Bean
    @Autowired
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
    	return emf.unwrap(SessionFactory.class);
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(dataSource);
        tm.setEntityManagerFactory(emf);
        //tm.setSessionFactory(sessionFactory);
        return tm;
    }
}
