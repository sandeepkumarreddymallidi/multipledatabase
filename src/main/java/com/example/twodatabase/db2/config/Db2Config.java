package com.example.twodatabase.db2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import javax.print.attribute.standard.MediaSize;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;
@Configuration  @EnableTransactionManagement  @EnableJpaRepositories(entityManagerFactoryRef = "bookEntityManagerFactory",
transactionManagerRef = "bookTransactionManager",basePackages = {"com.example.twodatabase.db2.repository"})
public class Db2Config {
    @Autowired Environment environment;
    @Bean(name = "bookDataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();  dataSource.setDriverClassName(environment.getProperty("spring.db2.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.db2.datasource.jdbcUrl"));  dataSource.setUsername(environment.getProperty("spring.db2.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.db2.datasource.password"));return dataSource;  }
    @Bean(name = "bookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter adaptor=new HibernateJpaVendorAdapter();  bean.setJpaVendorAdapter(adaptor);
        HashMap<String,String> hashMap=new HashMap<>();  hashMap.put("hibernate.hbm2ddl.auto","create"); hashMap.put("hibernate.show_sql","true");
        hashMap.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");  bean.setDataSource(dataSource());
        bean.setPackagesToScan("com.example.twodatabase.db2.entities"); bean.setJpaPropertyMap(hashMap);   return bean; }
    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager booTransactionManager(){
        JpaTransactionManager manager=new JpaTransactionManager();   manager.setEntityManagerFactory(bookEntityManagerFactoryBean().getObject());
        return manager;
    }
}
