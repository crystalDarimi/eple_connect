package com.crystal.eple.config;

import com.crystal.eple.security.JwtAuthenticationFilter;
import com.crystal.eple.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    private JwtAuthenticationFilter jwtAuthenticationFilter;




    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http 시큐리티 빌더
        http.cors().disable(); //기본 cors
        http.csrf().disable();// 사용 안함
        http.httpBasic().disable()
                .authorizeRequests() // 요청에 관한 사용 권한체크
                .antMatchers("/","/eple/v1/auth/**","/error").permitAll()
                .antMatchers(HttpMethod.GET,"/eple/v1/mystudent/lecture").hasRole("STUDENT")
                .antMatchers(HttpMethod.GET,"/eple/v1/calendar/schedule").hasRole("STUDENT")
                .antMatchers("/eple/v1/calendar/**","/eple/v1/mystudent/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);




        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
    }


}
