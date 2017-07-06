package com.reports.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.reports.components.entity.Country;
import com.reports.components.entity.Location;
import com.reports.components.entity.NetworkSession;
import com.reports.components.entity.ReportEntity;
import com.reports.components.entity.Request;
import com.reports.components.entity.User;
import com.reports.components.entity.UserGroup;

/**
 * Service for SessionFactory
 * 
 * @author cortes
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.reports" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	/**
	 * Contain sessionProperti config
	 * 
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.reports" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		// Entitys for mapping
		sessionFactory.setAnnotatedClasses(Country.class, Location.class, NetworkSession.class, Request.class, User.class, UserGroup.class, ReportEntity.class);

		return sessionFactory;
	}

	/**
	 * Connect to the DB
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
		return properties;
	}

	/**
	 * Configuration of transactionManager
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
