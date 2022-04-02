package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.FilesService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final FilesService filesService;

    @GetMapping("/{file-name:.+}")
    public String getFile(@PathVariable("file-name") String fileName, Model model) {
        model.addAttribute("account", filesService.getAccount(fileName));
        return "profile";
    }
}
