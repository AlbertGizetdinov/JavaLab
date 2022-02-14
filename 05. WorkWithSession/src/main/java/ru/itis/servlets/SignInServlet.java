package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signInService = applicationContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm form = SignInForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        Optional<User> userOptional = signInService.signIn(form);
        if (userOptional.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isAuthenticated", true);
            session.setAttribute("user", userOptional.get());
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/signIn?error");
        }
    }
}
