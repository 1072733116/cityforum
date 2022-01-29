package com.city.forum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-22 23:02
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${file.filePath}")
    private String uploadFolder;

    @Value("${file.fileUrl}")
    private String fileUrl;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUrl).addResourceLocations("file:" + uploadFolder);
        super.addResourceHandlers(registry);
    }

//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(responseBodyConverter());
//        super.configureMessageConverters(converters);
//    }
//    @Bean
//    public HttpMessageConverter responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }


}
