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
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                //users
                .requestMatchers("/api/singfusion/users/curr-user-details").permitAll()
                .requestMatchers("/api/singfusion/users/logout/{id}").permitAll()
                .requestMatchers("/api/singfusion/users/add").permitAll()
                .requestMatchers("/api/singfusion/users/all").permitAll()
                .requestMatchers("/api/singfusion/users/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/users/delete/{id}").permitAll()
                //kit
                .requestMatchers("/api/singfusion/kit/add").permitAll()
                .requestMatchers("/api/singfusion/kit/all").permitAll()
                .requestMatchers("/api/singfusion/kit/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/kit/delete/{id}").permitAll()
                //poste
                .requestMatchers("/api/singfusion/postetravail/add").permitAll()
                .requestMatchers("/api/singfusion/postetravail/all").permitAll()
                .requestMatchers("/api/singfusion/postetravail/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/postetravail/delete/{id}").permitAll()
                //poste
                .requestMatchers("/api/singfusion/outilsinformatique/add").permitAll()
                .requestMatchers("/api/singfusion/outilsinformatique/all").permitAll()
                .requestMatchers("/api/singfusion/outilsinformatique/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/outilsinformatique/delete/{id}").permitAll()
                //role  add
                .requestMatchers("/api/singfusion/role/add").permitAll()
                //kit
                .requestMatchers("/api/singfusion/kit/add").permitAll()
                .requestMatchers("/api/singfusion/kit/all").permitAll()
                //acces
                .requestMatchers("/api/singfusion/acces/add").permitAll()
                .requestMatchers("/api/singfusion/acces/all").permitAll()
                //questions
                .requestMatchers("/api/singfusion/questions/add").permitAll()
                .requestMatchers("/api/singfusion/questions/all").permitAll()
                //reponses
                .requestMatchers("/api/singfusion/reponses/add").permitAll()
                .requestMatchers("/api/singfusion/reponses/all").permitAll()
                //quiz
                .requestMatchers("/api/singfusion/quiz/add").permitAll()
                .requestMatchers("/api/singfusion/quiz/all").permitAll()
                .requestMatchers("/api/singfusion/quiz/findbyetapes/{etape_integration}").permitAll()
                //quiz result
                .requestMatchers("/api/singfusion/quizresult/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/quizresult/all").permitAll()
                .requestMatchers("/api/singfusion/quizresult/findbyuser/{id}").permitAll()
                //formalites
                .requestMatchers("/api/singfusion/formalites/add").permitAll()
                .requestMatchers("/api/singfusion/formalites/all").permitAll()
                .requestMatchers("/api/singfusion/formalites/update/{id}").permitAll()
                .requestMatchers("/api/singfusion/formalites/delete/{id}").permitAll()
                .requestMatchers("/api/singfusion/formalites/findbyuser/{id}").permitAll()
                //reponses
                .requestMatchers("/api/singfusion/reponses/add").permitAll()
                .requestMatchers("/api/singfusion/reponses/all").permitAll()
                //responsabilite
                .requestMatchers("/api/singfusion/responsabilite/add").permitAll()
                .requestMatchers("/api/singfusion/responsabilite/all").permitAll()
                //presentation generale
                .requestMatchers("/api/singfusion/presentationgenerale/add").permitAll()
                .requestMatchers("/api/singfusion/presentationgenerale/all").permitAll()
                .requestMatchers("/api/singfusion/presentationgenerale/findbyuser/{id}").permitAll()
                //presentation generale  contenus
                .requestMatchers("/api/singfusion/document/add").permitAll()
                .requestMatchers("/api/singfusion/document/all").permitAll()
                .requestMatchers("/api/singfusion/document/findbyuser/{id}").permitAll()
                //presentation generale  contenus
                .requestMatchers("/api/singfusion/contenus/add").permitAll()
                .requestMatchers("/api/singfusion/contenus/all").permitAll()
                .requestMatchers("/api/singfusion/contenus/findbyc/{id}").permitAll()

                .requestMatchers("/api/singfusion/faq/add").permitAll()
                .requestMatchers("/api/singfusion/faq/all").permitAll()
                .requestMatchers("/api/singfusion/faq/findbycategorie/{categorie}").permitAll()

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
                .requestMatchers("/api/singfusion/role/all").permitAll()
                .requestMatchers("/api/singfusion/users/authenticate").permitAll()
                .requestMatchers("/api/singfusion/users/logout/{id}").permitAll()
                .requestMatchers("/api/singfusion/users/add").permitAll()
                .requestMatchers("/api/singfusion/users/all").permitAll()
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
