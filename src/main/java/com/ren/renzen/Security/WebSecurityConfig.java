package com.ren.renzen.Security;

import com.ren.renzen.Services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.CREATE_ARTICLE_DRAFT_FROM_APP;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.OPEN_ARTICLE_DRAFT_FROM_APP;
import static com.ren.renzen.additional.KEYS.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //unauthorized handler
    final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    final CustomUserDetailService customUserDetailService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter();
//    }

    public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, CustomUserDetailService customUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.customUserDetailService = customUserDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        //this.jwtAuthenticationFilter = new JwtAuthenticationFilter()

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //super.configure(auth);
        authenticationManagerBuilder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        //TODO change some of these so they can only be used when logged in

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(

                        OPEN_ARTICLE_DRAFT_FROM_APP+"/**",
                        CREATE_ARTICLE_DRAFT_FROM_APP+"/**",

                        "/getArticleField/**",
                        "/getArticleField/**",

                        "/newCreateArticle/**",
                        "/getCommunityStreamComponentCO/",
                        "/getCommunityStreamComponentCO/**",

                        "/getCommunityTabComponent/",
                        "/getCommunityTabComponent/**",

                        "/getProfileStreamComponentCO/",
                        "/getProfileStreamComponentCO/**",

                        "/getProfileTabComponentCO/",
                        "/getProfileTabComponentCO/**",

                        "/createArticle",
                        "/addScreenshotToArticle/",
                        "/addScreenshotToArticle/**",

                        "/getArticleStreamComponentCO/",
                        "/getArticleStreamComponentCO/**",

                        "/getArticleTabComponentCO/",
                        "/getArticleTabComponentCO/**",

                        "/joinCommunity",
                        "/createCommunity",

                        "/getCommunityStreamComponentCO/**",
                        "/getCommunityStreamComponentCO/",

                        "/getCommunityTabComponent/**",
                        "/getCommunityTabComponent/",

                        "/addBookmark",
                        "/getHomeStreams",
                        "/getHomeStreams/**",

                        "/addImage",

                        "/likeArticle/",
                        "/dislikeArticle/",

                        "/",
                        "/favicon.ico",
                                               "/**/*.png", "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/built/**") // might have to add more
                .permitAll()
                .antMatchers(SIGN_UP_URLS, LOGIN_URLS, HOME_PAGE).permitAll() //permits login
                .anyRequest().authenticated(); //all others require authentication


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

}