package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {

    private final static String USER_ATTRIBUTE_NAME = "user";

    private final static String USER_COOKIE_NAME = "userCookie";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (processUserCookie(request) != null) {
            request.setAttribute(USER_ATTRIBUTE_NAME, processUserCookie(request));
        }

        chain.doFilter(request, response);
    }

    private String processUserCookie(HttpServletRequest request) {
        String user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(USER_COOKIE_NAME)) {
                    user = cookie.getValue();
                }
            }
        }
        return user;
    }

    @Override
    public void destroy() {

    }
}
