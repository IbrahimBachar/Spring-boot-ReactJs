package com.clinic_system.clinic_alshifa.security;

import com.clinic_system.clinic_alshifa.model.MyAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private MyAppUserService appUserService;

    @Autowired
    private final CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public AuthenticationProvider authenticationProvider(MyAppUserService myAppUserService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService((UserDetailsService) myAppUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm ->{
                    httpForm.loginPage("/req/login").permitAll();
                    httpForm.successHandler(successHandler);// Use custom success handler for role-based redirection
                })

//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())   //A revoir
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")  // Custom login page for OAuth2 login
                        .defaultSuccessUrl("/index", true)  // Redirect after successful OAuth login
                )
                .authorizeHttpRequests(registry ->{
                    // Define access based on roles
                    registry.requestMatchers("/", "/landing", "/css/**", "/js/**").permitAll();
                    registry.requestMatchers("/req/signup", "/forgot-password", "/reset-password", "/download-pdf").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/doctor/**").hasRole("DOCTOR");
                    registry.requestMatchers("/patient/**").hasRole("PATIENT");

                    registry.requestMatchers(HttpMethod.DELETE, "/delete/**").hasRole("ADMIN");

                    registry.anyRequest().authenticated();
                })

                .build();
    }



}

//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/admin/**").hasRole("ADMIN")
//                .antMatchers("/api/doctor/**").hasRole("DOCTOR")
//                .antMatchers("/api/patient/**").hasRole("PATIENT")
//                .antMatchers("/api/public/**", "/login", "/signup").permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .logout();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
