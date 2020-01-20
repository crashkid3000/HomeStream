package com.homestream.HomeStream.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Some further configuration for Hibernate
 * @author baeldung
 * @see <a href="https://github.com/eugenp/tutorials/blob/51352c470f98c417bb4ecb9b3150eb9f167bc361/spring-data-rest/src/main/java/com/baeldung/config/DbConfig.java">Github ref</a>
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.homestream.HomeStream.dao")
public class DbConfig {

    @Autowired
    private Environment env;

    /**
     * Reads the configuration from <code>application.properties</code> and <code>persistence.properties</code>, and loads these into memory.
     * The following properties will be read:
     * <ul>
     *     <li><code>driverClassName</code></li>
     *     <li><code>url</code></li>
     *     <li><code>user</code></li>
     *     <li><code>password</code></li>
     * </ul>
     * @return A nicely configured DataSource
     */
    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword((env.getProperty("password")));
        return dataSource;
    }

}
