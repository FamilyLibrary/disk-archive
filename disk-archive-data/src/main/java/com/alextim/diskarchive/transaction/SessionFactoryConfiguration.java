package com.alextim.diskarchive.transaction;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class SessionFactoryConfiguration {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PropertiesFactoryBean hibernateProperties;

	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() throws IOException {
		final AnnotationSessionFactoryBean sessionFactoryBean = new AnnotationSessionFactoryBean();

		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setAnnotatedPackages(new String[]{"com.alextim.diskarchive.entity"});
		
		sessionFactoryBean.setHibernateProperties((Properties)hibernateProperties.getObject());

		return (SessionFactory) sessionFactoryBean.getObject();
	}
}
