package de.fhopf.blog.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig {

    @Configuration
    public static class ApiConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // doesn't really make sense to protect a REST API using form login but it is just for illustration
            http
                    .formLogin()
                    .and()
                    .authorizeRequests().antMatchers("/secret/**").authenticated()
                    .and()
                    .authorizeRequests().antMatchers("/**").permitAll();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }
    }

    @Order(1)
    @Configuration
    public static class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/management/**")
                    .httpBasic()
                    .and()
                    .authorizeRequests().antMatchers("/management/**").authenticated();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
        }
    }
}
