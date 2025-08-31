package com.chronosgit.expenso.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.chronosgit.expenso.Main;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig cfg = new HikariConfig();

        // cfg.setJdbcUrl(System.getenv("DB_URL"));
        // cfg.setUsername(System.getenv("DB_USER"));
        // cfg.setPassword(System.getenv("DB_PASSWORD"));

        Properties props = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Failed to read config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }

        cfg.setJdbcUrl(props.getProperty("db.url"));
        cfg.setUsername(props.getProperty("db.user"));
        cfg.setPassword(props.getProperty("db.password"));
        cfg.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.maximumPoolSize")));
        cfg.setMinimumIdle(Integer.parseInt(props.getProperty("db.pool.minimumIdle")));
        cfg.setIdleTimeout(Integer.parseInt(props.getProperty("db.pool.idleTimeout")));

        dataSource = new HikariDataSource(cfg);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        dataSource.close();
    }
}
