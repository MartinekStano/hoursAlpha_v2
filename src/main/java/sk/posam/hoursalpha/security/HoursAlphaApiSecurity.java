package sk.posam.hoursalpha.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class HoursAlphaApiSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/v1/hoursAplha/noAuth/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();
    }
}
