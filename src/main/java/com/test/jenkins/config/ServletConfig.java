package com.test.jenkins.config;

import com.test.jenkins.utils.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@PropertySource("classpath:test.properties")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.test.jenkins.controller"})
public class ServletConfig implements WebMvcConfigurer {

    @Value("${test.hello}")
    private String hello;


    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Test.hello = hello;
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter(true));
        converters.add(new ByteArrayHttpMessageConverter());
    }

}
