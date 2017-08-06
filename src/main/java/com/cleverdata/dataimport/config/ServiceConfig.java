/**
 * IBM CSOB BPM2
 *
 * Copyright (c) 2016 International Business Machines. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of IBM.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with IBM.
 *
 * Note to US Government users: Documentation related to restricted rights
 * Use, duplication or disclosure is subject to restrictions set forth in
 * GSA ADP Schedule with IBM Corp.
 *
 * IBM MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.cleverdata.dataimport.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = {"com.cleverdata.dataimport.repository"})
@Configuration
@ComponentScan(basePackages = {"com.cleverdata.dataimport"}, lazyInit = true)
@EnableScheduling
public class ServiceConfig {

	private JndiTemplate jndiTemplate = new JndiTemplate();

//	private int cacheEvictionMinutes = 30;

//	@Bean(value = "guavaCacheManager")
//	public CacheManager cacheManager() {
//		GuavaCacheManager cacheManager =  new GuavaCacheManager();
//		cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES));
//		return cacheManager;
//	}




	@Bean
	public DataSource dataSource() throws NamingException {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
				.addScript("db/sql/create-db.sql")
				.addScript("db/sql/insert-data.sql")
				.build();
		return db;
	}

	// Start WebServer, access http://localhost:8082
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server startDBManager() throws SQLException {
		return Server.createWebServer("-webAllowOthers","-webDaemon");
	}


	@Bean
	public EntityManagerFactory entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.cleverdata.dataimport.domain");
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.ejb.HibernatePersistence.class);
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}


	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "validate");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "false");
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws NamingException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}





}
