package com.example.twodatabase.db1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
@Configuration @EnableTransactionManagement @EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryBean", basePackages = {
        "com.example.twodatabase.db1.repository"},transactionManagerRef = "transactionManager")
public class Db1Config {
    @Autowired Environment environment;
    @Primary @Bean public DataSource dataSource(){
       DriverManagerDataSource dataSource= new DriverManagerDataSource();  dataSource.setDriverClassName(environment.getProperty("spring.db1.datasource.driver-class-name"));
       dataSource.setUrl(environment.getProperty("spring.db1.datasource.jdbcUrl"));  dataSource.setUsername(environment.getProperty("spring.db1.datasource.username"));
       dataSource.setPassword(environment.getProperty("spring.db1.datasource.password"));  return dataSource;  }
    @Primary @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
       LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
       JpaVendorAdapter adaptor=new HibernateJpaVendorAdapter();  bean.setJpaVendorAdapter(adaptor);
       HashMap<String,String> hashMap=new HashMap<>();hashMap.put("hibernate.hbm2ddl.auto","create");
       hashMap.put("hibernate.show_sql","true");hashMap.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
       bean.setDataSource(dataSource());bean.setPackagesToScan("com.example.twodatabase.db1.entities");
       bean.setJpaPropertyMap(hashMap);  return bean; }
    @Primary @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager=new JpaTransactionManager();  manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager; }
}
