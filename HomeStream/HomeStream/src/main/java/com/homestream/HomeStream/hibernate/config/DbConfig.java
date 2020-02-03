package com.homestream.HomeStream.hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Some further configuration for Hibernate
 * @author baeldung
 * @see <a href="https://github.com/eugenp/tutorials/blob/51352c470f98c417bb4ecb9b3150eb9f167bc361/spring-data-rest/src/main/java/com/baeldung/config/DbConfig.java">Github ref</a>
 */
@Configuration
public class DbConfig {



    /*
    private static final String[] packagesToScan = {
            "com.homestream.HomeStream.dao",
            "com.homestream.HomeStream.entity",
            "com.homestream.HomeStream.vo"
    };

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:./res/db.sqlite");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();


        //Setting further hibernaste properties
        Properties hibernateProperties = new Properties();

        //set session factory
        lsfb.setDataSource(dataSource());
        lsfb.setPackagesToScan("com.homestream.HomeStream.dao", "com.homestream.HomeStream.entity", "com.homestream.HomeStream.vo");
        lsfb.setHibernateProperties(hibernateProperties);
        return lsfb;

    }

    //TODO: Does this actually work?
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setDataSource(dataSource());
        em.setPackagesToScan(packagesToScan);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    Properties additionalProperties(){
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", "com.homestream.HomeStream.hibernate.sqlite.SQLiteDialect");

        return p;
    }
    */
}
