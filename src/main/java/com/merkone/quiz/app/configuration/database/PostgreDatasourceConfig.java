package com.merkone.quiz.app.configuration.database;

import java.util.HashMap;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author vescudero
 */
@Configuration
public class PostgreDatasourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreDatasourceConfig.class);

    @Qualifier("CyberArkPostgrePassword")
    private String cyberArkPostgrePassword;

    @Value("${postgre.datasource.url}")
    private String databaseUrl;

    @Value("${postgre.datasource.username}")
    private String username;

    @Value("${postgre.datasource.password}")
    private String password;

    @Value("${postgre.datasource.driver-class-name}")
    private String className;

    @Value("${postgre.maxactive}")
    private Integer maxActive;

    @Value("${postgre.minidle}")
    private Integer minIdle;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation}")
    private String nonContextualCreation;

    @Value("${spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults}")
    private String useJdbcMetadataDefaults;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgreDataSource());
        em.setPackagesToScan("com.merkone.quiz.domain");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", useJdbcMetadataDefaults);
        properties.put("hibernate.jdbc.lob.non_contextual_creation", nonContextualCreation);

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource postgreDataSource() {
        LOGGER.info("Loading PostgreDataSource");
        return getPostgreConnectionPool().getDataSource();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean("postgreConnectionPool")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PostgreConnectionPool getPostgreConnectionPool() {
        LOGGER.info("Loading PostgreConnectionPool");
        String pwd = (cyberArkPostgrePassword != null && !cyberArkPostgrePassword.isEmpty()) ? cyberArkPostgrePassword : password;
        return new PostgreConnectionPool(className, databaseUrl, username, pwd, maxActive, minIdle);
    }

}
