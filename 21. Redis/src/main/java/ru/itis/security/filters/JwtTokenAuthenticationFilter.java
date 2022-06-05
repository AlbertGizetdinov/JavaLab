package ru.itis.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.dto.EmailPasswordDto;
import ru.itis.models.Account;
import ru.itis.security.details.AccountUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Slf4j
public class JwtTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    private final String secretKey;

    private final Long tokenLifeTime;

    public JwtTokenAuthenticationFilter(AuthenticationManager manager, ObjectMapper objectMapper, String secretKey, Long tokenLifeTime) {
        super(manager);
        this.objectMapper = objectMapper;
        this.secretKey = secretKey;
        this.tokenLifeTime = tokenLifeTime;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            EmailPasswordDto emailPassword = objectMapper.readValue(request.getReader(), EmailPasswordDto.class);
            log.info("Attempt authentication - email {}", emailPassword.getEmail());

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(emailPassword.getEmail(), emailPassword.getPassword());

            return super.getAuthenticationManager().authenticate(token);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AccountUserDetails userDetails = (AccountUserDetails) authResult.getPrincipal();
        Account account = userDetails.getAccount();

        String token = JWT.create()
                .withSubject(account.getId().toString())
                .withClaim("email", account.getEmail())
                .withClaim("role", account.getRole().toString())
                .withClaim("state", account.getState().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenLifeTime))
                .sign(Algorithm.HMAC256(secretKey));

        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("token", token));
    }
}

