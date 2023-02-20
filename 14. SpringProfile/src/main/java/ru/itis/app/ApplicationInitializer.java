package ru.itis.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.config.ApplicationDevConfig;
import ru.itis.config.ApplicationProductionConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        springWebContext.getEnvironment().setActiveProfiles(System.getenv().get("SPRING_PROFILE"));
        springWebContext.register(ApplicationDevConfig.class);
        springWebContext.register(ApplicationProductionConfig.class);
        ContextLoaderListener listener = new ContextLoaderListener(springWebContext);
        servletContext.addListener(listener);

        Dynamic dispatcherServlet = servletContext.addServlet("dispatcher",
                new DispatcherServlet(springWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
