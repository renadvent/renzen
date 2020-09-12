package com.ren.renzen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class ReactAndSpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactAndSpringDataRestApplication.class, args);
    }


//    @Bean
//    public class FilterRegistrationBean<ForwardedHeaderFilter> config() {
//        FilterRegistrationBean<ForwardedHeaderFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new ForwardedHeaderFilter());
//        return bean;
//    }

}