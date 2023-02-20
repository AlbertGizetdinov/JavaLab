package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.exceptrions.ConfirmException;
import ru.itis.services.SignUpService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/confirm")
public class ConfirmController {

    private final SignUpService signUpService;

    @GetMapping("/{confirm-code}")
    public String getConfirmPage(@PathVariable("confirm-code") String confirmCode) {
        try {
            signUpService.confirmAccount(confirmCode);
        } catch (ConfirmException exception) {
            return "wrongConfirmPage";
        }
        return "redirect:/signUp";
    }
}
