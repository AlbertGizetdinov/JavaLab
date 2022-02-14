package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private ServletContext servletContext;
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        userRepository = springContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
        } else {
            response.sendRedirect(servletContext.getContextPath() + "/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = userRepository.findByName(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            Cookie cookie = new Cookie("userCookie", username);
            cookie.setMaxAge(10);
            response.addCookie(cookie);

            response.sendRedirect(servletContext.getContextPath() + "/profile");
        } else {
            request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
        }
    }
}
