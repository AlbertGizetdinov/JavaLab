package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.repositories.WordCountImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/words")
public class WordServlet extends HttpServlet {

    private WordCountImpl wordCount;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.wordCount = springContext.getBean(WordCountImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String folder = request.getParameter("folder");
        wordCount.setFolder(folder);
        Map<String, Map<String, Integer>> words = wordCount.start();
        request.setAttribute("words", words);
        request.getRequestDispatcher("/jsp/words.jsp").forward(request, response);
    }
}
