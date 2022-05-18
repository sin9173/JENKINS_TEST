package com.test.jenkins.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        Class[] servletContext = new Class[] {ServletConfig.class};

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(servletContext);

        FrameworkServlet dispatcherServlet = new DispatcherServlet(servletAppContext);

        ServletRegistration.Dynamic registration = context.addServlet("dispatcher", dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping(new String[] {"/"});
        registration.setAsyncSupported(true);

        registerCharacterEncodingFilter(context);

    }

    private void registerCharacterEncodingFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());

        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
    }
}
