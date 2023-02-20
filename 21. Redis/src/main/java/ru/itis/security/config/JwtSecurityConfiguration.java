package ru.itis.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.repositories.BlackListRepository;
import ru.itis.security.filters.JwtTokenAuthenticationFilter;
import ru.itis.security.filters.JwtTokenAuthorizationFilter;
import ru.itis.security.filters.JwtTokenLogoutFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_FILTER_PROCESSES_URL = "/login";

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.token.life.time.millis}")
    private Long tokenLifeTime;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final BlackListRepository blackListRepository;

    @Autowired
    private UserDetailsService accountUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtTokenAuthenticationFilter authenticationFilter =
                new JwtTokenAuthenticationFilter(authenticationManagerBean(), objectMapper, secretKey, tokenLifeTime);

        JwtTokenAuthorizationFilter authorizationFilter =
                new JwtTokenAuthorizationFilter(objectMapper, secretKey, tokenLifeTime, blackListRepository);

        authenticationFilter.setFilterProcessesUrl(LOGIN_FILTER_PROCESSES_URL);

        JwtTokenLogoutFilter logoutFilter =
                new JwtTokenLogoutFilter(blackListRepository);

        http.csrf().disable();
        http.logout().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(authenticationFilter);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(logoutFilter, JwtTokenAuthorizationFilter.class);

        http.authorizeRequests()
                .antMatchers(LOGIN_FILTER_PROCESSES_URL + "/**").permitAll()
                .antMatchers("/api/authors/**").hasAuthority("ADMIN")
                .antMatchers("/api/posts").hasAuthority("USER");
    }
}

