package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.SignUpDto;
import ru.itis.services.FilesService;
import ru.itis.services.SignUpService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    private final FilesService filesService;

    @GetMapping
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping
    public String signUp(SignUpDto accountForm, @RequestParam("file") MultipartFile multipart) {
        String storageFileName = filesService.upload(multipart);
        signUpService.signUp(accountForm, storageFileName);
        return "redirect:/profile/" + storageFileName;
    }
}

