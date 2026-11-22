package ru.danilscheglov.todo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration implements AutoCloseable {

    private static final String DEFAULT_CONFIG = "application.properties";
    private static final String DEV_CONFIG = "application-dev.properties";

    private final HikariDataSource dataSource;

    public DatabaseConfiguration() {
        Properties properties = loadProperties();
        HikariConfig hikariConfig = buildHikariConfig(properties);
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream defaultStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_CONFIG)) {
            if (defaultStream == null) {
                throw new IllegalStateException("Configuration file not found in classpath: " + DEFAULT_CONFIG);
            }
            properties.load(defaultStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load configuration from " + DEFAULT_CONFIG, e);
        }

        try (InputStream devStream = getClass().getClassLoader().getResourceAsStream(DEV_CONFIG)) {
            if (devStream != null) {
                properties.load(devStream);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load dev configuration from " + DEV_CONFIG, e);
        }

        return properties;
    }

    private HikariConfig buildHikariConfig(Properties properties) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(getRequiredProperty(properties, "database.url"));
        config.setUsername(getRequiredProperty(properties, "database.username"));
        config.setPassword(getRequiredProperty(properties, "database.password"));

        config.setPoolName(properties.getProperty("database.pool.name", "TodoHikariPool"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("database.pool.max-size", "5")));
        config.setMinimumIdle(Integer.parseInt(properties.getProperty("database.pool.min-idle", "1")));
        config.setConnectionTimeout(Long.parseLong(properties.getProperty("database.pool.connection-timeout", "3000")));

        return config;
    }

    private String getRequiredProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required configuration property: " + key);
        }
        return value;
    }
}