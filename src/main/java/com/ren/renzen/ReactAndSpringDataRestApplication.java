package com.ren.renzen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class ReactAndSpringDataRestApplication {
//
//    public class AppInitializer implements WebApplicationInitializer {

//        @Override
//        public void onStartup(ServletContext servletContext) {
//            final AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
//
//            final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
//            registration.setLoadOnStartup(1);
//            registration.addMapping("/");
//
//            File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
//            MultipartConfigElement multipartConfigElement = new  MultipartConfigElement(uploadDirectory.getAbsolutePath(), 100000, 100000 * 2, 100000 / 2);
//
//            registration.setMultipartConfig(multipartConfigElement);
//        } }

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