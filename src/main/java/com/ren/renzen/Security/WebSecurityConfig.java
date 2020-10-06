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

import static com.ren.renzen.additional.KEYS.SIGN_UP_URLS;

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

    public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, CustomUserDetailService customUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.customUserDetailService = customUserDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/favicon.ico") // might have to add more
        .permitAll()
                .antMatchers(SIGN_UP_URLS).permitAll() //permits login
                .anyRequest().authenticated(); //all others require authentication


        //http.authorizeRequests().anyRequest().permitAll();

        //http.cors().disable();

//        http
//                .authorizeRequests()
//                .antMatchers("/", "/getHomeStreams").permitAll()
//                .anyRequest().permitAll();

//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}