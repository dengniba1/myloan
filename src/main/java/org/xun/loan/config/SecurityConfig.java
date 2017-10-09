/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xun.loan.config;

import org.xun.loan.security.MainAppAuthenticationSuccessHandler;
import org.xun.loan.security.MainAppUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.xun.loan.security.MainAppAuthenticationFailureHandler;
import org.xun.loan.security.RongUserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Joe Grandja
 */
@EnableWebSecurity
@EnableJpaAuditing
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_PROCESSING_URL = "/login";

    @Autowired
    private RongUserDetailsService userDetailsService;
    @Autowired
    private MainAppAuthenticationSuccessHandler mainAppAuthenticationSuccessHandler;

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MainAppUsernamePasswordAuthenticationFilter mainAppUsernamePasswordAuthenticationFilter = new MainAppUsernamePasswordAuthenticationFilter();
        mainAppUsernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        mainAppUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(LOGIN_PROCESSING_URL));// 只处理LOGIN_PROCESSING_URL过来的请求
        mainAppUsernamePasswordAuthenticationFilter
                .setAuthenticationSuccessHandler(mainAppAuthenticationSuccessHandler);
        mainAppUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new MainAppAuthenticationFailureHandler());
        mainAppUsernamePasswordAuthenticationFilter.setUsernameParameter("username");
        mainAppUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
        mainAppUsernamePasswordAuthenticationFilter.afterPropertiesSet();

        http.headers().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**", "/product/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/logins").failureUrl("/logins").and()
                .addFilter(mainAppUsernamePasswordAuthenticationFilter).httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MainAppPasswordEncoder());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false).userDetailsService(userDetailsService);
    }

}
