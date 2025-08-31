package com.chronosgit.expenso.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.Context;
import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServlet;

import com.chronosgit.expenso.controller.TestController;
import com.chronosgit.expenso.filter.GlobalExceptionFilter;
import com.chronosgit.expenso.filter.ResponseSetupFilter;

public class TomcatConfig {
    public static Tomcat configurateTomcat() {
        Tomcat tc = new Tomcat();

        tc.setPort(8081);
        tc.getConnector();

        Context ctx = tc.addContext("", null);

        registerFilter(
                ctx, "globalExcepionFilter",
                GlobalExceptionFilter.class,
                Optional.empty(),
                "/*");

        Map<String, String> corsParams = new HashMap<>();
        corsParams.put("cors.allow.origin.patterns", ".*");
        corsParams.put("cors.allowed.methods", "GET,POST,PUT,DELETE,OPTIONS");
        corsParams.put("cors.support.credentials", "true");
        corsParams.put("cors.allowed.headers", "Content-Type,Authorization");

        registerFilter(
                ctx, "corsFilter",
                CorsFilter.class,
                Optional.of(corsParams),
                "/api/*");

        registerFilter(
                ctx, "responseSetupFilter",
                ResponseSetupFilter.class,
                Optional.empty(),
                "/api/*");

        registerServlet(
                ctx,
                "test",
                TestController.class,
                "/api/test");

        return tc;
    }

    public static void registerFilter(Context ctx, String name, Class<? extends Filter> filterClass,
            Optional<Map<String, String>> params, String... urlPatterns) {
        FilterDef def = new FilterDef();
        def.setFilterName(name);
        def.setFilterClass(filterClass.getName());

        params.ifPresent(m -> {
            for (Map.Entry<String, String> e : m.entrySet()) {
                def.addInitParameter(e.getKey(), e.getValue());
            }
        });

        ctx.addFilterDef(def);

        FilterMap map = new FilterMap();
        map.setFilterName(name);
        for (String p : urlPatterns) {
            map.addURLPattern(p);
        }
        ctx.addFilterMap(map);
    }

    public static void registerServlet(Context ctx, String name, Class<? extends HttpServlet> servletClass,
            String urlPattern) {
        Tomcat.addServlet(ctx, name, servletClass.getName()).setLoadOnStartup(1);

        ctx.addServletMappingDecoded(urlPattern, name);
    }
}
