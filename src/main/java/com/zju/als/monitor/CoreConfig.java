package com.zju.als.monitor;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.Filter;
import java.io.IOException;

/**
 * Created by Administrator on 2015/11/3.
 */
@Configuration
public class CoreConfig {
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public PropertyPlaceholderConfigurer properties() throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource rs[] =resolver.getResources("classpath:*.properties");
        ppc.setLocations(rs);
        return ppc;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityLayoutViewResolver viewResolver = new VelocityLayoutViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".vm");
        viewResolver.setDateToolAttribute("date");
        viewResolver.setNumberToolAttribute("number");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setRequestContextAttribute("rc");
        viewResolver.setLayoutUrl("layout.vm");
        viewResolver.setCache(false);

        return viewResolver;
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/assets/");
            }
        };
    }
}
