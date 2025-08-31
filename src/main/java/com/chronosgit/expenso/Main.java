package com.chronosgit.expenso;

import org.apache.catalina.startup.Tomcat;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chronosgit.expenso.config.DatabaseConfig;
import com.chronosgit.expenso.config.MarkerConfig;
import com.chronosgit.expenso.config.TomcatConfig;
import com.chronosgit.expenso.exception.ThreadExceptionHandler;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        try {
            Tomcat tc = TomcatConfig.configurateTomcat();

            ThreadExceptionHandler.handleUncaughtExceptions();

            try (Connection conn = DatabaseConfig.getConnection()) {
                logger.info(MarkerConfig.DB, "Database and Hikari pool are working");
            } catch (SQLException e) {
                logger.error(
                        MarkerConfig.ERROR,
                        "Failed to connect to the DB",
                        e);

                System.exit(1);
            }

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info(
                        MarkerConfig.SHUTDOWN,
                        "JVM is shutting down. Cleaning resources");

                try {
                    tc.stop();
                    tc.destroy();
                } catch (LifecycleException ex) {
                    logger.error(
                            MarkerConfig.ERROR,
                            "Failed to clean Tomcat");
                }

                DatabaseConfig.closeDataSource();
            }));

            try {
                tc.start();

                logger.info(
                        MarkerConfig.STARTUP,
                        "Tomcat started on port {}",
                        tc.getConnector().getPort());

                logger.info(
                        MarkerConfig.STARTUP,
                        "Server is now awaiting requests...");

                tc.getServer().await();
            } catch (LifecycleException e) {
                logger.error(
                        MarkerConfig.ERROR,
                        "Tomcat failed to start: {}",
                        e);

                System.exit(1);
            }
        } catch (Exception e) {
            logger.error(MarkerConfig.ERROR, "Exception in main thread", e);
        }
    }
}