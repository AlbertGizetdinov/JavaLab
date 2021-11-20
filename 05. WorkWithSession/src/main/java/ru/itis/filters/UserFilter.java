package ru.itis.filters;

import ru.itis.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {

    private static final String USER_ATTRIBUTE_NAME = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if (session.getAttribute("isAuthenticated") != null && session.getAttribute("isAuthenticated").equals(true)) {
            User user = (User) session.getAttribute("user");
            request.setAttribute(USER_ATTRIBUTE_NAME, user);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
