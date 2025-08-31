package com.chronosgit.expenso.config;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MarkerConfig {
    public static final Marker AUTH = MarkerFactory.getMarker("AUTH");
    public static final Marker PERMISSION = MarkerFactory.getMarker("PERMISSION");
    public static final Marker API_ERROR = MarkerFactory.getMarker("API_ERROR");

    public static final Marker HTTP = MarkerFactory.getMarker("HTTP");
    public static final Marker FILTER = MarkerFactory.getMarker("FILTER");

    public static final Marker DB = MarkerFactory.getMarker("DB");
    public static final Marker QUERY = MarkerFactory.getMarker("QUERY");

    public static final Marker CONFIG = MarkerFactory.getMarker("CONFIG");
    public static final Marker STARTUP = MarkerFactory.getMarker("STARTUP");
    public static final Marker ERROR = MarkerFactory.getMarker("ERROR");
    public static final Marker SHUTDOWN = MarkerFactory.getMarker("SHUTDOWN");
}