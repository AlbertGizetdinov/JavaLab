package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String folder = request.getParameter("folder");
        WordCount wordCount = new WordCount(folder);
        String responseHtml = wordCount.start();
        response.setContentType("text/html");
        response.getWriter().println(responseHtml);
    }
}
