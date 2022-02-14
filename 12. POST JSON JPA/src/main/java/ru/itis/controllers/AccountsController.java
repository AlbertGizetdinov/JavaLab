package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.AccountDto;
import ru.itis.models.Account;
import ru.itis.services.AccountsService;


@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAccountsPage(Model model) {
        model.addAttribute("accounts", accountsService.getAccounts());
        return "accounts";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String setAccount(AccountDto accountDto) {
        Account account = Account.builder()
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .email(accountDto.getEmail())
                .build();
        accountsService.addAccount(account);
        return "redirect:/accounts";
    }
}
