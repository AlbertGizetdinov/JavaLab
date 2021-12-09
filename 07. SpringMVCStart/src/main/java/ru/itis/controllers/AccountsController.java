package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.models.Account;
import ru.itis.services.AccountsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountsController implements Controller {

    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getMethod().equals("POST")) {
            Account account = Account.builder()
                    .firstName(request.getParameter("first_name"))
                    .lastName(request.getParameter("last_name"))
                    .build();
            accountsService.addAccount(account);
            response.sendRedirect("/accounts");
        }
        modelAndView.addObject("accounts", accountsService.getAccounts());
        modelAndView.setViewName("accounts");
        return modelAndView;
    }
}
