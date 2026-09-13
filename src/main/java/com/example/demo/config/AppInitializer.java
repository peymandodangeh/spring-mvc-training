package com.example.demo.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Replaces web.xml: registers the DispatcherServlet and wires it to
 * the Java-based (annotation) configuration in {@link WebConfig}.
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeClientInfo(true);
        FilterRegistration.Dynamic filterRegistration =
                servletContext.addFilter("requestLoggingFilter", loggingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }
}
