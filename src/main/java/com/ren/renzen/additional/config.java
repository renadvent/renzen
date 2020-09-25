//package com.ren.renzen.additional;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.util.unit.DataSize;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//import java.io.File;
//
//
//@Configuration
//
//public class config extends WebMvcAutoConfiguration {
//
//    @Bean (name="multipartResolver")
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        return multipartResolver;
//    }
//
//
//
//
////    public static final String MAX_FILE_SIZE = "256MB";
////    public static final String MAX_REQUEST_SIZE = "256MB";
////    public static final String FILE_SIZE_THRESHOLD = "256MB";
////
////    @Bean
////    MultipartConfigElement multipartConfigElement() {
////        //String absTempPath = new File(createdDir).getAbsolutePath();
////        MultipartConfigFactory factory = new MultipartConfigFactory();
////        factory.setMaxFileSize(DataSize.parse(MAX_FILE_SIZE));
////        factory.setMaxRequestSize(DataSize.parse(MAX_REQUEST_SIZE));
////        factory.setFileSizeThreshold(DataSize.parse(FILE_SIZE_THRESHOLD));
////        //factory.setLocation(absTempPath);
////        return factory.createMultipartConfig();
////    }
//
//
//
////    @Bean
////    public StandardServletMultipartResolver multipartResolver() {
////        return new StandardServletMultipartResolver();
////    }
//
//}
