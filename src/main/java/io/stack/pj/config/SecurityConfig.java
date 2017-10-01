package io.stack.pj.config;

import io.stack.pj.user.Role;
import io.stack.pj.user.auth.CustomAuthFailureHandler;
import io.stack.pj.user.auth.CustomAuthSuccessHandler;
import io.stack.pj.user.impl.DefaultUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
 *
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultUserDetailService userDetailService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomAuthFailureHandler authFailureHandler;

    @Autowired
    private CustomAuthSuccessHandler authSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/swagger-ui.html", "/monitoring").hasAuthority(Role.ROLE_ADMIN.toString())
                .antMatchers("/user/**").hasAuthority(Role.ROLE_USER.name())
                .and()
                .formLogin().loginPage("/login").permitAll()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt password encryption strength can be increased by providing parameter > 10 in constructor, but it's side effect is that authentication time increases hence slower login process.
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/css/**",
                        "/js/**", "/images/**");
    }
}
