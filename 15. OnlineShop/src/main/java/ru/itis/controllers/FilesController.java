package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.FilesService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/files")
public class FilesController {

    private final FilesService fileService;

    @GetMapping
    public String getFilesUploadPage() {
        return "file_upload_page";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile[] multiparts) {
        for(MultipartFile multipartFile: multiparts) {
            fileService.upload(multipartFile);
        }
        return "redirect:/files";
    }
}

