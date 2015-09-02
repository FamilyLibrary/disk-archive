package com.alextim.diskarchive.transaction;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.AbstractSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class SessionFactoryConfiguration {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PropertiesFactoryBean hibernateProperties;

	@Bean(name = "sessionFactory")
	public AbstractSessionFactoryBean sessionFactory() throws IOException {
		final AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();

		factory.setDataSource(dataSource);
		factory.setPackagesToScan(new String[]{"com.alextim.diskarchive.entity"});
		
		factory.setHibernateProperties(hibernateProperties.getObject());

		return factory;
	}
}
