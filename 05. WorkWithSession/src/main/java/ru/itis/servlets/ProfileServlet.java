package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private ServletContext servletContext;
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.userRepository = springContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("user") != null) {
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signIn");
        }
    }
}
