package com.singfusion.singfusion.security;
import com.singfusion.singfusion.security.jwt.JwtAuthEntryPoint;
import com.singfusion.singfusion.security.jwt.JwtAuthTokenFilter;
import com.singfusion.singfusion.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Nouveauté de Spring 3: définir SecurityFilterChain en tant que bean qui remplace void configure
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/interbtp/users/authenticate").permitAll()
//                        .requestMatchers("/api/interbtp/users/forgot-password").permitAll()
//                        .requestMatchers("/api/interbtp/users/reset-password").permitAll()
//                        .requestMatchers("/api/interbtp/users/all").permitAll()
//                        .requestMatchers("/api/interbtp/role/add").permitAll()
//                        .requestMatchers("/api/interbtp/users/checkidentity").permitAll()
//                        .requestMatchers("/api/interbtp/users/checkpassport").permitAll()
//                        .requestMatchers("/api/interbtp/publication/all").permitAll()
//                        .requestMatchers("/api/interbtp/publication/add").permitAll()
//                        .requestMatchers("/api/interbtp/publication/findbyid/{id}").permitAll()
//                        .requestMatchers("/api/interbtp/publication/update/{id}").permitAll()
//                        .requestMatchers("/api/interbtp/publication/delete/{id}").permitAll()
//                        .anyRequest().authenticated()
//                )
//
//                .exceptionHandling((exception)-> exception.authenticationEntryPoint(jwtAuthEntryPoint).accessDeniedPage("/error/accedd-denied"))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
////                .httpBasic(Customizer.withDefaults());
////                .formLogin(Customizer.withDefaults());
//        return http.build();
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                //users
                .requestMatchers("/api/singfusion/users/authenticate").permitAll()
                .requestMatchers("/api/singfusion/users/logout/{id}").permitAll()
                .requestMatchers("/api/singfusion/users/add").permitAll()
                .requestMatchers("/api/singfusion/users/all").permitAll()
                .requestMatchers("/api/singfusion/users/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/users/delete/{id}").permitAll()
                //role  count-user
                .requestMatchers("/api/singfusion/role/add").permitAll()
                .requestMatchers("/api/singfusion/role/all").permitAll()
                //kit
                .requestMatchers("/api/singfusion/kit/add").permitAll()
                .requestMatchers("/api/singfusion/kit/all").permitAll()
                //acces
                .requestMatchers("/api/singfusion/acces/add").permitAll()
                .requestMatchers("/api/singfusion/acces/all").permitAll()
                //verification session identity findbyiduser
                .requestMatchers("/api/singfusion/verificationidentity/findbyid/{id}").permitAll()
                .requestMatchers("/api/singfusion/verificationidentity/findbyuser/{id}").permitAll()

//                .requestMatchers("/images/**").permitAll()  getprojetouvert  /update/{id}
//                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                //lists
                .requestMatchers("/hashing").permitAll()
                .requestMatchers("/create-payment-intent").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/checkout.js").permitAll()
                .requestMatchers("/checkout.css").permitAll()
                .requestMatchers("/success.html").permitAll()
                .requestMatchers("/cancel.html").permitAll();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =  http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authManagerBuilder.build();

//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
    }
}
