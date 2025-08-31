package com.chronosgit.expenso.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chronosgit.expenso.config.MarkerConfig;

public class ThreadExceptionHandler {
    public static void handleUncaughtExceptions() {
        Logger logger = LoggerFactory.getLogger(ThreadExceptionHandler.class);

        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
            logger.error(
                    MarkerConfig.ERROR,
                    "Uncaught exception in the thread: {}",
                    thread.getName(),
                    e);
        });
    }
}
