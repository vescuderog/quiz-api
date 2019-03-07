package com.merkone.quiz.app.configuration.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vescudero
 */
public class PostgreConnectionPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreConnectionPool.class);

    private DataSource dataSource = null;

    @SuppressWarnings("rawtypes")
    public PostgreConnectionPool(String driver, String url, String user, String password,
            int maxActive, /*int maxIdle,*/ int minIdle) {
        LOGGER.debug("Creación del pool de conexiones a Postgre");
        LOGGER.debug("PostgreDriver :       [{}]", driver);
        LOGGER.debug("PostgreUrl :          [{}]", url);
        LOGGER.debug("PostgreUser :         [{}]", user);
        LOGGER.debug("PostgreMaxActive :    [{}]", maxActive);
//        LOGGER.debug("PostgreMaxIdle :      [{}]", maxIdle);
        LOGGER.debug("PostgreminIdle :      [{}]", minIdle);

        Properties props = new Properties();
        props.put("driverClassName", driver);
        props.put("jdbcUrl", url);
        props.put("username", user);
        props.put("password", password);
        props.put("maximumPoolSize", maxActive);
        props.put("minimumIdle", minIdle);

        HikariConfig config = new HikariConfig(props);
        dataSource = new HikariDataSource(config);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
