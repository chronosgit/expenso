package com.chronosgit.expenso.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(System.getProperty("DB_URL"));
        cfg.setUsername(System.getProperty("DB_USER"));
        cfg.setPassword(System.getProperty("DB_PASSWORD"));
        cfg.setMaximumPoolSize(10);
        cfg.setIdleTimeout(60000);
        cfg.setMinimumIdle(2);

        dataSource = new HikariDataSource(cfg);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        dataSource.close();
    }
}
