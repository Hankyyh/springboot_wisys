package com.nebula.wisys.web.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMessageConverterConfig implements WebMvcConfigurer {
    
    // To handle String type case in http response processing
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}