package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Account;

import javax.servlet.http.HttpServletResponse;

public interface FilesService {
    String upload(MultipartFile multipart);

    void addFileToResponse(String fileName, HttpServletResponse response);

    Account getAccount(String fileName);
}
