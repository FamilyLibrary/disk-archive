package com.alextim.diskarchive.transaction;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfiguration implements TransactionManagementConfigurer{
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		final HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setDataSource(dataSource);
		tm.setSessionFactory(sessionFactory);
		return tm;
	}

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
