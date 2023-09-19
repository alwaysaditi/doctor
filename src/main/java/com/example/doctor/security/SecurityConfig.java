package com.example.doctor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager()
//    {
//        UserDetails aditi = User.builder()
//                .username("alwaysaditi")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","ADMIN","USER")
//                .build();
//
//
//        UserDetails arvind = User.builder()
//                .username("arvindp611")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","DOCTOR","USER")
//                .build();
//        UserDetails ashwini = User.builder()
//                .username("ashwini1404")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","NURSE","USER")
//                .build();
//        UserDetails isha = User.builder()
//                .username("isha2610")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","PHARMACIST","USER")
//                .build();
//        UserDetails rita = User.builder()
//                .username("rita1610")
//                .password("{noop}test123")
//                .roles("PATIENT","USER")
//                .build();
//
//        UserDetails jyoti= User.builder()
//                .username("jyoti211")
//                .password("{noop}test123")
//                .roles("EMPLOYEE , LABDOCTOR,USER")
//                .build();
//
//return new InMemoryUserDetailsManager(aditi,arvind,ashwini,isha,rita,jyoti);
//    }

@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) // this datasource is configured from the
        // details given in application.properties file
    {
      JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

      jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw , 'true' as enabled from members where user_id=?");
      jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id , role from roles where user_id=?");
      return jdbcUserDetailsManager;
    }
// by default , any person who initially registers will have the role of user
    // the admin can configure other roles to that person, after which the user role granted
    // to him will be deleted
    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->configurer
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/home").hasAnyRole("USER","DOCTOR","ADMIN","NURSE","PHARMACIST","PATIENT")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .requestMatchers("/nurse/**").hasRole("NURSE")
                        .requestMatchers("/pharmacist/**").hasRole("PHARMACIST")
                        .anyRequest().authenticated()
        )
                .exceptionHandling(exception ->exception.accessDeniedPage("/access-denied"))
                .formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/authenticateUser")
                        .defaultSuccessUrl("/home")
                .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    // right now any request is required to be authenticated. later , the home pages and the login and
    // register pages should be accessible to all users
    // later on configure different default success URLS depending upon the signed in users
    // the permissions for register and login pages and home page that is using permitAll()
    // should be BEFORE anyRequest().authenticated

}
