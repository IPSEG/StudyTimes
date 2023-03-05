package com.ipseg.studyTime.framework.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final PropertiesPropertySourceLoader propertySourceLoader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Resource resource;
        resource = new ClassPathResource("properties/custom.properties");

        try {
            //propertySource를 불러온다.
            List<PropertySource<?>> propertySource = propertySourceLoader.load("CustomProperties", resource);
            if (propertySource != null) {
                for(PropertySource<?> property : propertySource) {
                    environment.getPropertySources().addLast(property);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Failed to load configuration from " + resource, ioe);
        }

    }
}
